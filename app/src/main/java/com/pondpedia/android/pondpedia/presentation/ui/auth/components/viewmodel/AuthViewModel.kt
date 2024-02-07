package com.pondpedia.android.pondpedia.presentation.ui.auth.components.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pondpedia.android.pondpedia.core.util.Resource
import com.pondpedia.android.pondpedia.data.remote.dto.auth.AuthResponse
import com.pondpedia.android.pondpedia.data.remote.dto.auth.login.LoginRequest
import com.pondpedia.android.pondpedia.data.remote.dto.auth.register.RegisterRequest
import com.pondpedia.android.pondpedia.domain.repository.AuthRepository
import com.pondpedia.android.pondpedia.domain.use_case.auth.signup.ValidateEmailUseCase
import com.pondpedia.android.pondpedia.domain.use_case.auth.signup.ValidateInformationSourceUseCase
import com.pondpedia.android.pondpedia.domain.use_case.auth.signup.ValidateNameUseCase
import com.pondpedia.android.pondpedia.domain.use_case.auth.signup.ValidateOccupationUseCase
import com.pondpedia.android.pondpedia.domain.use_case.auth.signup.ValidatePasswordUseCase
import com.pondpedia.android.pondpedia.domain.use_case.auth.signup.ValidatePhoneNumberUseCase
import com.pondpedia.android.pondpedia.domain.use_case.auth.signup.ValidateRepeatedPasswordUseCase
import com.pondpedia.android.pondpedia.domain.use_case.auth.signup.ValidateTermsUseCase
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.data.AuthState
import com.pondpedia.android.pondpedia.presentation.ui.auth.components.util.AuthEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository,
    private val validateNameUseCase: ValidateNameUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateRepeatedPasswordUseCase: ValidateRepeatedPasswordUseCase,
    private val validateOccupationUseCase: ValidateOccupationUseCase,
    private val validateInformationSourceUseCase: ValidateInformationSourceUseCase,
    private val validateTermsUseCase: ValidateTermsUseCase
): ViewModel() {

    ////////////////////////////| Custom API Authentication |////////////////////////////

    private val _response = MutableStateFlow(AuthResponse())
    val response = _response.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AuthResponse())

    ////////////////////////////| Custom API Authentication Function |////////////////////////////

    fun googleAuthRegister(){}

    ////////////////////////////| Firebase Email and Password Authentication |////////////////////////////

    init {
        getAuthState()
    }

    fun getAuthState() = repo.getAuthState(viewModelScope)

    val isEmailVerified get() = repo.currentUser?.isEmailVerified ?: false

    ////////////////////////////| Normal ViewModel Things |////////////////////////////

    private val _state = MutableStateFlow(AuthState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AuthState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()


    ////////////////////////////| Normal ViewModel OnEvent |////////////////////////////

    fun onEvent(event: AuthEvent) {
        when(event) {
            is AuthEvent.SetName -> {
                _state.update {
                    it.copy(
                        name = event.value
                    )
                }
            }
            is AuthEvent.SetEmail -> {
                _state.update {
                    it.copy(
                        email = event.value
                    )
                }
            }
            is AuthEvent.SetPhoneNumber -> {
                _state.update {
                    it.copy(
                        phoneNumber = event.value
                    )
                }
            }
            is AuthEvent.SetPassword -> {
                _state.update {
                    it.copy(
                        password = event.value
                    )
                }
            }
            is AuthEvent.SetRepeatedPassword -> {
                _state.update {
                    it.copy(
                        repeatedPassword = event.value
                    )
                }
            }
            is AuthEvent.SetOccupation -> {
                _state.update {
                    it.copy(
                        occupation = event.value
                    )
                }
            }
            is AuthEvent.SetInformationSource -> {
                _state.update {
                    it.copy(
                        informationSource = event.value
                    )
                }
            }
            is AuthEvent.SetAcceptTerms -> {
                _state.update {
                    it.copy(
                        acceptedTerms = event.value
                    )
                }
            }
            is AuthEvent.SetPhotoUrl -> {
                _state.update {
                    it.copy(
                        photoUrl = event.value
                    )
                }
            }
            is AuthEvent.SetFirebaseId -> {
                _state.update {
                    it.copy(
                        firebaseId = event.value
                    )
                }
            }
            is AuthEvent.SetPreferences -> {
                _state.update {
                    it.copy(
                        preferences = event.value
                    )
                }
            }
            is AuthEvent.SetSignInId -> {
                _state.update {
                    it.copy(
                        signInId = event.value
                    )
                }
            }
            is AuthEvent.SetSignInPassword -> {
                _state.update {
                    it.copy(
                        signInPassword = event.value
                    )
                }
            }
            is AuthEvent.SignIn -> {
                signIn()
            }
            is AuthEvent.SignUp -> {
                signUp()
            }
            is AuthEvent.Reset -> {
                _state.update {
                    it.copy(
                        name = "",
                        phoneNumber = "",
                        email = "",
                        password = "",
                        repeatedPassword = "",
                        occupation = "",
                        informationSource = ""
                    )
                }
            }

            is AuthEvent.DismissSignUpCommonDialog -> {
                _state.update {
                    it.copy(
                        isSignUpSuccessful = false,
                        isSignUpError = false,
                    )
                }
            }

            AuthEvent.DismissSignInCommonDialog -> {
                _state.update {
                    it.copy(
                        isSignInSuccessful = false,
                        isSignInError = false,
                    )
                }
            }

            AuthEvent.ResetSignInState -> {
                _state.update {
                    it.copy(
                        isSignInError = false,
                        isSignInSuccessful = false,
                        signInError = "",
                        signInSuccess = "",
                        email = "",
                        password = ""
                    )
                }
            }
        }
    }

    private fun signUp() {
        val nameResult = validateNameUseCase(state.value.name)
        val emailResult = validateEmailUseCase(state.value.email)
        val phoneNumberResult = validatePhoneNumberUseCase(state.value.phoneNumber)
        val passwordResult = validatePasswordUseCase(state.value.password)
        val repeatedPasswordResult = validateRepeatedPasswordUseCase(state.value.password, state.value.repeatedPassword)
        val occupationResult = validateOccupationUseCase(state.value.occupation)
        val informationSourceResult = validateInformationSourceUseCase(state.value.informationSource)
//        val acceptedTermsResult = validateTermsUseCase(state.value.acceptedTerms)

        val hasError = listOf(
            nameResult,
            emailResult,
            phoneNumberResult,
            passwordResult,
            repeatedPasswordResult,
            occupationResult,
            informationSourceResult,
//            acceptedTermsResult
        ).any {!it.successful}

        if (hasError) {
            _state.update { it.copy(
                nameError = nameResult.errorMessage,
                emailError = emailResult.errorMessage,
                phoneNumberError = phoneNumberResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage,
                occupationError = occupationResult.errorMessage,
                informationSourceError = informationSourceResult.errorMessage,
//                acceptedTermsError = acceptedTermsResult.errorMessage,
            ) }
            return
        }

        Log.d("AuthViewModel", state.value.name)
        Log.d("AuthViewModel", state.value.email)
        Log.d("AuthViewModel", state.value.phoneNumber)
        Log.d("AuthViewModel", state.value.password)
        Log.d("AuthViewModel", state.value.repeatedPassword)
        Log.d("AuthViewModel", state.value.occupation)
        Log.d("AuthViewModel", state.value.informationSource)
        Log.d("AuthViewModel", state.value.acceptedTerms.toString())

        viewModelScope.launch {
            _state.update { it.copy(
                isSignUpLoading = true
            ) }
            val body = RegisterRequest(
                name = state.value.name,
                username = state.value.email.substringBefore("@"),
                phoneNumber = state.value.phoneNumber,
                email = state.value.email,
                password = state.value.password,
                occupation = state.value.occupation,
            )
            val result = repo.register(body)
            _state.update { it.copy(
                isSignUpLoading = false
            ) }
            when(result) {
                is Resource.Success -> {
                    _state.update { it.copy(
                        isSignUpSuccessful = true,
                        signUpSuccess = result.data ?: "Terjadi kesalahan"
                    ) }
                }
                is Resource.Error -> {
                    _state.update { it.copy(
                        isSignUpError = true,
                        signUpError = result.message ?: "Terjadi kesalahan"
                    ) }
                }
                else -> {}
            }
        }
    }

    private fun signIn() {
        val emailResult = validateEmailUseCase(state.value.email)
        val passwordResult = validatePasswordUseCase(state.value.password)

        val hasError = listOf(
            emailResult,
            passwordResult,
        ).any {!it.successful}

        if (hasError) {
            _state.update { it.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
            ) }
            return
        }

        Log.d("AuthViewModel", state.value.email)
        Log.d("AuthViewModel", state.value.password)

        viewModelScope.launch {
            _state.update { it.copy(
                isSignInLoading = true
            ) }
            val body = LoginRequest(
                email = state.value.email,
                password = state.value.password,
            )
            val result = repo.login(body)
            _state.update { it.copy(
                isSignInLoading = false
            ) }
            when(result) {
                is Resource.Success -> {
                    _state.update { it.copy(
                        isSignInSuccessful = true,
                        signInSuccess = result.data ?: "Berhasil masuk"
                    ) }
                }
                is Resource.Error -> {
                    _state.update { it.copy(
                        isSignInError = true,
                        signInError = result.message ?: "Terjadi kesalahan"
                    ) }
                }
                else -> {}
            }
        }
    }

    fun isUserLoggedIn() = runBlocking { repo.isUserLoggedIn() }

    fun checkTokenValidity() = runBlocking { repo.checkTokenValidity() }

    sealed interface ValidationEvent {
        data object Success: ValidationEvent
    }
}
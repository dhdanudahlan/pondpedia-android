package com.pondpedia.android.pondpedia.presentation.ui.auth.components.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.pondpedia.android.pondpedia.domain.model.Response.*
import com.pondpedia.android.pondpedia.domain.repository.AuthRepository
import com.pondpedia.android.pondpedia.domain.repository.OneTapSignInResponse
import com.pondpedia.android.pondpedia.domain.repository.SignInWithGoogleResponse
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
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository,
    val oneTapClient: SignInClient,
    private val validateNameUseCase: ValidateNameUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateRepeatedPasswordUseCase: ValidateRepeatedPasswordUseCase,
    private val validateOccupationUseCase: ValidateOccupationUseCase,
    private val validateInformationSourceUseCase: ValidateInformationSourceUseCase,
    private val validateTermsUseCase: ValidateTermsUseCase
): ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AuthState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    val isUserAuthenticated get() = repo.isUserAuthenticatedInFirebase

    var oneTapSignInResponse by mutableStateOf<OneTapSignInResponse>(Success(null))
        private set
    var signInWithGoogleResponse by mutableStateOf<SignInWithGoogleResponse>(Success(false))
        private set

    fun oneTapSignIn() = viewModelScope.launch {
        oneTapSignInResponse = Loading
        oneTapSignInResponse = repo.oneTapSignInWithGoogle()
    }

    fun signInWithGoogle(googleCredential: AuthCredential) = viewModelScope.launch {
        oneTapSignInResponse = Loading
        signInWithGoogleResponse = repo.firebaseSignInWithGoogle(googleCredential)
    }

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
                Log.d("AuthViewModel", "OnEvent(SignIn) being Called")

            }
            is AuthEvent.SignUp -> {
                Log.d("AuthViewModel", "OnEvent(SignUp) being Called")
                signUp()
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
        val acceptedTermsResult = validateTermsUseCase(state.value.acceptedTerms)

        val hasError = listOf(
            nameResult,
            emailResult,
            phoneNumberResult,
            passwordResult,
            repeatedPasswordResult,
            occupationResult,
            informationSourceResult,
            acceptedTermsResult
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
                acceptedTermsError = acceptedTermsResult.errorMessage,
            ) }

            Log.d("AuthViewModel", state.value.name)
            Log.d("AuthViewModel", state.value.email)
            Log.d("AuthViewModel", state.value.phoneNumber)
            Log.d("AuthViewModel", state.value.password)
            Log.d("AuthViewModel", state.value.repeatedPassword)
            Log.d("AuthViewModel", state.value.occupation)
            Log.d("AuthViewModel", state.value.informationSource)
            Log.d("AuthViewModel", state.value.acceptedTerms.toString())
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed interface ValidationEvent {
        data object Success: ValidationEvent
    }
}
package com.pondpedia.android.pondpedia.presentation.screens.auth.components.viewmodel

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
import com.pondpedia.android.pondpedia.presentation.screens.auth.components.data.AuthState
import com.pondpedia.android.pondpedia.presentation.screens.auth.components.util.AuthEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository,
    val oneTapClient: SignInClient
): ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AuthState())

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
            is AuthEvent.SetPasswordConfirmation -> {
                _state.update {
                    it.copy(
                        passwordConfirmation = event.value
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
            AuthEvent.SignIn -> {

            }
            AuthEvent.SignUp -> {

            }

        }
    }
}
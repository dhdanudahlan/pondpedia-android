package com.pondpedia.android.pondpedia.presentation.ui.auth.sign_in.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.pondpedia.android.pondpedia.core.util.Resource
import com.pondpedia.android.pondpedia.data.remote.dto.auth.AuthResponse
import com.pondpedia.android.pondpedia.data.remote.dto.auth.UserRegistrationRequest
import com.pondpedia.android.pondpedia.domain.model.auth.Response
import com.pondpedia.android.pondpedia.domain.model.auth.Response.Loading
import com.pondpedia.android.pondpedia.domain.model.auth.Response.Success
import com.pondpedia.android.pondpedia.domain.repository.AuthRepository
import com.pondpedia.android.pondpedia.domain.repository.OneTapSignInResponse
import com.pondpedia.android.pondpedia.domain.repository.SignInResponse
import com.pondpedia.android.pondpedia.domain.repository.SignInWithGoogleResponse
import com.pondpedia.android.pondpedia.domain.repository.SignInWithPondPediaApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repo: AuthRepository,
    val oneTapClient: SignInClient
): ViewModel() {

    var signInApiWithGoogleResponse by mutableStateOf<SignInWithPondPediaApiResponse>(Success(null))
    private set

    ////////////////////////////| Firebase Google Authentication |////////////////////////////

    fun registerGoogleAuth(
        token: String,
        userRegistrationRequest: UserRegistrationRequest
    ): Response<AuthResponse> {
        val authResponse: Response<AuthResponse> = Success(null)
        viewModelScope.launch {
            val autoResponse = repo.registerGoogleAuth(
                token = token,
                userRegistrationRequest = userRegistrationRequest
            )
        }
        return authResponse
    }
    ////////////////////////////| Firebase Google Authentication |////////////////////////////

    var oneTapSignInResponse by mutableStateOf<OneTapSignInResponse>(Success(null))
        private set
    var signInWithGoogleResponse by mutableStateOf<SignInWithGoogleResponse>(Success(null))
        private set

    fun oneTapSignIn() = viewModelScope.launch {
        oneTapSignInResponse = Loading
        oneTapSignInResponse = repo.oneTapSignInWithGoogle()
        Log.d("AuthViewModel", "Sign In with Google")
    }

    fun signInWithGoogle(googleCredential: AuthCredential) = viewModelScope.launch {
        oneTapSignInResponse = Loading
        signInWithGoogleResponse = repo.firebaseSignInWithGoogle(googleCredential)
    }


    ////////////////////////////| Firebase Email and Password Authentication |////////////////////////////

    var signInResponse by mutableStateOf<SignInResponse>(Success(false))
        private set

    fun signInWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        signInResponse = Loading
        signInResponse = repo.firebaseSignInWithEmailAndPassword(email, password)
    }
}
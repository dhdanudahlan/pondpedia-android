package com.pondpedia.android.pondpedia.domain.repository

import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.pondpedia.android.pondpedia.core.util.Resource
import com.pondpedia.android.pondpedia.data.remote.dto.auth.AuthResponse
import com.pondpedia.android.pondpedia.data.remote.dto.auth.UserRegistrationRequest
import com.pondpedia.android.pondpedia.data.remote.dto.auth.login.LoginRequest
import com.pondpedia.android.pondpedia.data.remote.dto.auth.register.RegisterRequest
import com.pondpedia.android.pondpedia.domain.model.auth.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

typealias OneTapSignInResponse = Response<BeginSignInResult>
typealias SignInWithGoogleResponse = Response<AuthResult>
typealias SignInWithPondPediaApiResponse = Response<AuthResponse>

typealias SignUpResponse = Response<Boolean>
typealias SendEmailVerificationResponse = Response<Boolean>
typealias SignInResponse = Response<Boolean>
typealias ReloadUserResponse = Response<Boolean>
typealias SendPasswordResetEmailResponse = Response<Boolean>
typealias RevokeAccessResponse = Response<Boolean>
typealias AuthStateResponse = StateFlow<Boolean>

interface AuthRepository {
    val isUserAuthenticatedInFirebase: Boolean

    suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): SignInWithGoogleResponse


    /* Firebase Email and Password Authentication */
    val currentUser: FirebaseUser?

    val authResponse: AuthResponse

    suspend fun loginGoogleAuth(token: String): Resource<AuthResponse>
    suspend fun registerGoogleAuth(token: String, userRegistrationRequest: UserRegistrationRequest): Resource<AuthResponse>


    suspend fun firebaseSignUpWithEmailAndPassword(email: String, password: String, name: String): SignUpResponse

    suspend fun firebaseUpdateName(name: String): SignUpResponse

    suspend fun sendEmailVerification(): SendEmailVerificationResponse

    suspend fun firebaseSignInWithEmailAndPassword(email: String, password: String): SignInResponse

    suspend fun reloadFirebaseUser(): ReloadUserResponse

    suspend fun sendPasswordResetEmail(email: String): SendPasswordResetEmailResponse

    fun signOut()

    suspend fun revokeAccess(): RevokeAccessResponse

    fun getAuthState(viewModelScope: CoroutineScope): AuthStateResponse

    suspend fun register(request: RegisterRequest): Resource<String>

    suspend fun login(request: LoginRequest): Resource<String>

    suspend fun isUserLoggedIn(): Boolean
}
package com.pondpedia.android.pondpedia.data.repository

import android.util.Log
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.haroldadmin.cnradapter.NetworkResponse
import com.pondpedia.android.pondpedia.core.util.Constants.CREATED_AT
import com.pondpedia.android.pondpedia.core.util.Constants.DISPLAY_NAME
import com.pondpedia.android.pondpedia.core.util.Constants.EMAIL
import com.pondpedia.android.pondpedia.core.util.Constants.PHOTO_URL
import com.pondpedia.android.pondpedia.core.util.Constants.SIGN_IN_REQUEST
import com.pondpedia.android.pondpedia.core.util.Constants.SIGN_UP_REQUEST
import com.pondpedia.android.pondpedia.core.util.Resource
import com.pondpedia.android.pondpedia.core.util.manager.ThreadManager
import com.pondpedia.android.pondpedia.core.util.manager.TokenManager
import com.pondpedia.android.pondpedia.data.remote.api.PondPediaApiService
import com.pondpedia.android.pondpedia.data.remote.dto.auth.AuthResponse
import com.pondpedia.android.pondpedia.data.remote.dto.auth.UserRegistrationRequest
import com.pondpedia.android.pondpedia.data.remote.dto.auth.login.LoginRequest
import com.pondpedia.android.pondpedia.data.remote.dto.auth.register.RegisterRequest
import com.pondpedia.android.pondpedia.domain.model.auth.Response.*
import com.pondpedia.android.pondpedia.domain.repository.AuthRepository
import com.pondpedia.android.pondpedia.domain.repository.OneTapSignInResponse
import com.pondpedia.android.pondpedia.domain.repository.SignInWithGoogleResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val api: PondPediaApiService,
    private var oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST)
    private var signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST)
    private var signUpRequest: BeginSignInRequest,
    private val tokenManager: TokenManager,
    private val threadManager: ThreadManager
//    private val db: FirebaseFirestore
) : AuthRepository {
    override val isUserAuthenticatedInFirebase = auth.currentUser != null

    override suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse {
        return try {
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            Success(signInResult)
        } catch (e: Exception) {
            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                Success(signUpResult)
            } catch (e: Exception) {
                Failure(e)
            }
        }
    }

    override suspend fun firebaseSignInWithGoogle(
        googleCredential: AuthCredential
    ): SignInWithGoogleResponse {
        return try {
            val authResult = auth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
//            if (isNewUser) {
//                registerGoogleAuth(
//                    authorization = authResult.user!!.uid,
//                    userRegistrationRequest = UserRegistrationRequest(
//                        name = authResult.user!!.displayName ?: "NAME",
//                        username = authResult.user!!.email ?: "USERNAME",
//                        phoneNumber = authResult.user!!.phoneNumber ?: "6212345678910",
//                        email = authResult.user!!.email ?: "EMAIL",
//                        occupation = "Unknown",
//                        informationSource = "Unknown",
//                        userPreferences = "{}"
//                    )
//                )
//            } else {
//                authResult.user?.let {
//                    loginGoogleAuth(
//                        authorization = it.uid
//                    )
//                }
//            }
            Success(authResult)
        } catch (e: Exception) {
            Failure(e)
        }
    }

//    private suspend fun addUserToFirestore() {
//        auth.currentUser?.apply {
//            val user = toUser()
//            db.collection(USERS).document(uid).set(user).await()
//        }
//    }
    override val currentUser: FirebaseUser?
        get() = auth.currentUser
    override val authResponse: AuthResponse
        get() = TODO("Not yet implemented")


    override suspend fun loginGoogleAuth(
        token: String
    ): Resource<AuthResponse> {
        val response = try {
            api.googleAuthLogin(token)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured. -> $e")
        }.body() ?: return Resource.Error(message = "Response is null")
        return Resource.Success(response)
    }
    override suspend fun registerGoogleAuth(
        token: String,
        userRegistrationRequest: UserRegistrationRequest
    ): Resource<AuthResponse> {
        val response = try {
            api.googleAuthRegister(
                token = token,
                body = userRegistrationRequest
            ).body()
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured. -> $e")
        }
        return Resource.Success(response!!)
    }
    override suspend fun firebaseSignUpWithEmailAndPassword(
        email: String, password: String, name: String
    ) = try {
        auth.createUserWithEmailAndPassword(email, password).await()
        auth.currentUser?.getIdToken(true)?.addOnCompleteListener {
            Log.d("AuthRepositoryImpl", "Token: ${it.result.token}")
        }
        firebaseUpdateName(name)
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun firebaseUpdateName(name: String) = try {
        Log.d("AuthRepositoryImpl", "firebaseUpdateName")

        val user = auth.currentUser
        val profileUpdates = userProfileChangeRequest {
            displayName = name
            photoUri = photoUri
        }
        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("AuthRepoImpl", "User profile updated.")
                }
            }
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun sendEmailVerification() = try {
        auth.currentUser?.sendEmailVerification()?.await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun firebaseSignInWithEmailAndPassword(
        email: String, password: String
    ) = try {
        auth.signInWithEmailAndPassword(email, password).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun reloadFirebaseUser() = try {
        auth.currentUser?.reload()?.await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun sendPasswordResetEmail(email: String) = try {
        auth.sendPasswordResetEmail(email).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun signOut(): Resource<Unit> {
        return when (val result = api.logout()) {
            is NetworkResponse.Success -> {
                auth.signOut()
                tokenManager.deleteToken()
                tokenManager.deleteUserId()
                Resource.Success(Unit)
            }
            is NetworkResponse.Error -> {
                val response = result.body
                Resource.Error(response?.errors?.first()?.message.orEmpty(), null)
            }
        }
    }

    override suspend fun revokeAccess() = try {
        auth.currentUser?.delete()?.await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override fun getAuthState(viewModelScope: CoroutineScope) = callbackFlow {
        val authStateListener = AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), auth.currentUser == null)

    override suspend fun register(request: RegisterRequest): Resource<String> {
        return try {
            when (val result = api.register(request)) {
                is NetworkResponse.Success -> {
                    val response = result.body
                    Resource.Success(response.message)
                }
                is NetworkResponse.Error -> {
                    val response = result.body
                    Resource.Error(response?.message.orEmpty(), null)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.orEmpty(), null)
        }
    }

    override suspend fun login(request: LoginRequest): Resource<String> {
        return try {
            when (val result = api.login(request)) {
                is NetworkResponse.Success -> {
                    val response = result.body
                    tokenManager.saveToken(response.token)
                    tokenManager.saveUserId(response.user?.id.orEmpty())

                    val isThreadCreated = createThread()
                    if (isThreadCreated) {
                        Resource.Success(response.message)
                    } else {
                        Resource.Error("Terjadi kesalahan membuat thread", null)
                    }
                }
                is NetworkResponse.Error -> {
                    val response = result.body
                    Resource.Error(response?.errors?.first()?.message.orEmpty(), null)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.orEmpty(), null)
        }
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return (tokenManager.getToken().first()?.isNotEmpty() ?: false)
    }

    override suspend fun checkTokenValidity() {
        try {
            when(val result = api.getUser()) {
                is NetworkResponse.Success -> {
                    val response = result.body.user
                    if (response == null) {
                        tokenManager.deleteToken()
                        tokenManager.deleteUserId()
                    }
                }
                is NetworkResponse.Error -> {
                    tokenManager.deleteToken()
                    tokenManager.deleteUserId()
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
            tokenManager.deleteToken()
            tokenManager.deleteUserId()
        }
    }

    private suspend fun createThread(): Boolean {
        val isThreadExist = threadManager.getThreadId().first() != null
        if (isThreadExist) return true
        return try {
            when (val response = api.createThread()) {
                is NetworkResponse.Success -> {
                    val thread = response.body
                    threadManager.saveThreadId(thread.threadId)
                    true
                }
                is NetworkResponse.Error -> false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}

fun FirebaseUser.toUser() = mapOf(
    DISPLAY_NAME to displayName,
    EMAIL to email,
    PHOTO_URL to photoUrl?.toString(),
    CREATED_AT to serverTimestamp()
)
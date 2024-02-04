package com.pondpedia.android.pondpedia.data.repository

import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.haroldadmin.cnradapter.NetworkResponse
import com.pondpedia.android.pondpedia.core.util.Constants.USERS
import com.pondpedia.android.pondpedia.core.util.Resource
import com.pondpedia.android.pondpedia.core.util.manager.TokenManager
import com.pondpedia.android.pondpedia.data.remote.api.PondPediaApiService
import com.pondpedia.android.pondpedia.data.remote.dto.auth.login.LoginResponse
import com.pondpedia.android.pondpedia.domain.model.auth.Farmer
import com.pondpedia.android.pondpedia.domain.model.auth.Response
import com.pondpedia.android.pondpedia.domain.model.auth.Response.Failure
import com.pondpedia.android.pondpedia.domain.model.auth.Response.Success
import com.pondpedia.android.pondpedia.domain.repository.ProfileRepository
import com.pondpedia.android.pondpedia.domain.repository.RevokeAccessResponseGoogle
import com.pondpedia.android.pondpedia.domain.repository.SignOutResponse
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    private var signInClient: GoogleSignInClient,
    private val api: PondPediaApiService,
//    private val db: FirebaseFirestore
) : ProfileRepository {
    override val displayName = auth.currentUser?.displayName.toString()
    override val photoUrl = auth.currentUser?.photoUrl.toString()

    override suspend fun signOut(): SignOutResponse {
        return try {
            oneTapClient.signOut().await()
            auth.signOut()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun revokeAccess(): RevokeAccessResponseGoogle {
        return try {
            auth.currentUser?.apply {
//                db.collection(USERS).document(uid).delete().await()
                signInClient.revokeAccess().await()
                oneTapClient.signOut().await()
                delete().await()
            }
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun getUser(): Resource<Farmer> {
        return try {
            when(val result = api.getUser()) {
                is NetworkResponse.Success -> {
                    val response = result.body.user?.toFarmer()
                    if (response != null) {
                        Resource.Success(response)
                    } else {
                        Resource.Error("User not found")
                    }
                }
                is NetworkResponse.Error -> {
                    Resource.Error(result.error?.message.orEmpty())
                }
            }

        } catch (e: Exception) {
            Resource.Error(e.localizedMessage.orEmpty())
        }
    }
}
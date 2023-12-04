package com.pondpedia.android.pondpedia.domain.repository

import com.pondpedia.android.pondpedia.domain.model.Response


typealias SignOutResponse = Response<Boolean>
typealias RevokeAccessResponse = Response<Boolean>

interface ProfileRepository {
    val displayName: String
    val photoUrl: String

    suspend fun signOut(): SignOutResponse

    suspend fun revokeAccess(): RevokeAccessResponse
}
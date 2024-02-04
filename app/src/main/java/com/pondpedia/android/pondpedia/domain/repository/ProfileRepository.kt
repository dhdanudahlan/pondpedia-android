package com.pondpedia.android.pondpedia.domain.repository

import com.pondpedia.android.pondpedia.core.util.Resource
import com.pondpedia.android.pondpedia.domain.model.auth.Farmer
import com.pondpedia.android.pondpedia.domain.model.auth.Response


typealias SignOutResponse = Response<Boolean>
typealias RevokeAccessResponseGoogle = Response<Boolean>

interface ProfileRepository {
    val displayName: String
    val photoUrl: String

    suspend fun signOut(): SignOutResponse

    suspend fun revokeAccess(): RevokeAccessResponseGoogle

    suspend fun getUser(): Resource<Farmer>
}
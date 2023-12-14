package com.pondpedia.android.pondpedia.data.remote.api

import com.pondpedia.android.pondpedia.data.remote.dto.auth.AuthResponseDto
import com.pondpedia.android.pondpedia.data.remote.dto.auth.UserRegistrationRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("oauth/register")
    suspend fun googleAuthRegister(
        @Header("Authorization") authorization: String,
        @Body body: UserRegistrationRequest
    ): AuthResponseDto

    @GET("oauth/login")
    suspend fun googleAuthLogin(
        @Header("Authorization") authorization: String
    ): AuthResponseDto

    companion object {
        const val BASE_URL = "https://main.devapi-pondpedia.com/"
    }
}
package com.pondpedia.android.pondpedia.data.remote.api

import com.pondpedia.android.pondpedia.data.remote.dto.auth.AuthResponse
import com.pondpedia.android.pondpedia.data.remote.dto.auth.UserRegistrationRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface PondPediaApiService {
    @POST("oauth/register")
    suspend fun googleAuthRegister(
        @Header("Authorization") token: String,
        @Body body: UserRegistrationRequest
    ): Response<AuthResponse>

    @GET("oauth/login")
    suspend fun googleAuthLogin(
        @Header("Authorization") token: String
    ): Response<AuthResponse>


    companion object {
        const val BASE_URL = "https://main.devapi-pondpedia.com/"
    }
}
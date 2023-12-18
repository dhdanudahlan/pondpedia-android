package com.pondpedia.android.pondpedia.data.remote.api

import com.pondpedia.android.pondpedia.data.remote.dto.auth.AuthResponse
import com.pondpedia.android.pondpedia.data.remote.dto.auth.UserRegistrationRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("oauth/register")
    suspend fun googleAuthRegister(
        @Header("Authorization") authorization: String,
        @Body body: UserRegistrationRequest
    ): AuthResponse

    @GET("oauth/login")
    suspend fun googleAuthLogin(
        @Header("Authorization") authorization: String
    ): Call<AuthResponse>

    companion object {
        const val BASE_URL = "https://main.devapi-pondpedia.com/"
    }
}
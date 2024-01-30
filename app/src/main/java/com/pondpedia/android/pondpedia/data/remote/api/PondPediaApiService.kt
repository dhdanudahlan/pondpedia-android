package com.pondpedia.android.pondpedia.data.remote.api

import com.haroldadmin.cnradapter.NetworkResponse
import com.pondpedia.android.pondpedia.data.remote.dto.auth.AuthResponse
import com.pondpedia.android.pondpedia.data.remote.dto.auth.UserRegistrationRequest
import com.pondpedia.android.pondpedia.data.remote.dto.auth.login.LoginErrorResponse
import com.pondpedia.android.pondpedia.data.remote.dto.auth.login.LoginRequest
import com.pondpedia.android.pondpedia.data.remote.dto.auth.login.LoginResponse
import com.pondpedia.android.pondpedia.data.remote.dto.auth.register.RegisterRequest
import com.pondpedia.android.pondpedia.data.remote.dto.auth.register.RegisterResponse
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

    @POST("users/create")
    suspend fun register(
        @Body request: RegisterRequest
    ): NetworkResponse<RegisterResponse, RegisterResponse>

    @POST("users/login")
    suspend fun login(
        @Body request: LoginRequest
    ): NetworkResponse<LoginResponse, LoginErrorResponse>

    companion object {
        @Deprecated(
            "Use BuildConfig.BASE_URL instead",
            ReplaceWith("BuildConfig.BASE_URL"),
            DeprecationLevel.WARNING
        )
        const val BASE_URL = "https://main.devapi-pondpedia.com/"
    }
}
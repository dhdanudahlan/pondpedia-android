package com.pondpedia.android.pondpedia.data.remote.api

import com.haroldadmin.cnradapter.NetworkResponse
import com.pondpedia.android.pondpedia.data.remote.dto.ai_chat.ChatRequest
import com.pondpedia.android.pondpedia.data.remote.dto.ai_chat.ChatResponse
import com.pondpedia.android.pondpedia.data.remote.dto.ai_chat.ThreadResponse
import com.pondpedia.android.pondpedia.data.remote.dto.auth.AuthResponse
import com.pondpedia.android.pondpedia.data.remote.dto.auth.UserRegistrationRequest
import com.pondpedia.android.pondpedia.data.remote.dto.base.BaseError
import com.pondpedia.android.pondpedia.data.remote.dto.auth.login.LoginRequest
import com.pondpedia.android.pondpedia.data.remote.dto.auth.login.LoginResponse
import com.pondpedia.android.pondpedia.data.remote.dto.auth.register.RegisterRequest
import com.pondpedia.android.pondpedia.data.remote.dto.auth.register.RegisterResponse
import com.pondpedia.android.pondpedia.data.remote.dto.base.BaseSuccess
import com.pondpedia.android.pondpedia.data.remote.dto.base.Docs
import com.pondpedia.android.pondpedia.data.remote.dto.pond.PondRequest
import com.pondpedia.android.pondpedia.data.remote.dto.pond.PondResponse
import com.pondpedia.android.pondpedia.data.remote.dto.pond.water.WaterRequest
import com.pondpedia.android.pondpedia.data.remote.dto.pond.water.WaterResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

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
    ): NetworkResponse<LoginResponse, BaseError>

    @POST("users/logout")
    suspend fun logout(): NetworkResponse<Unit, BaseError>

    @GET("users/me")
    suspend fun getUser(): NetworkResponse<LoginResponse, BaseError>

    @POST("ponds")
    suspend fun createPond(
        @Body request: PondRequest
    ): NetworkResponse<Unit, BaseError>

    @GET("ponds")
    suspend fun getPonds(
    ): NetworkResponse<Docs<PondResponse>, BaseError>

    @GET("ponds/{id}")
    suspend fun getPond(
        @Path("id") id: String
    ): NetworkResponse<PondResponse, BaseError>

    @DELETE("ponds/{id}")
    suspend fun deletePond(
        @Path("id") id: String
    ): NetworkResponse<Unit, BaseError>

    @POST("ponds/{id}/water")
    suspend fun addWaterRecord(
        @Path("id") pondId: Long,
        @Body request: WaterRequest
    ): NetworkResponse<BaseSuccess<WaterResponse>, BaseError>

    @GET("ponds/{id}/water")
    suspend fun getWaterRecords(
        @Path("id") pondId: Long
    ): NetworkResponse<BaseSuccess<List<WaterResponse>>, BaseError>

    @POST("users/assistant/thread")
    suspend fun createThread(): NetworkResponse<ThreadResponse, BaseError>

    @POST("users/assistant/{id}")
    suspend fun sendMessage(
        @Path("id") id: String,
        @Body request: ChatRequest
    ): NetworkResponse<BaseSuccess<ChatResponse>, BaseError>

    companion object {
        @Deprecated(
            "Use BuildConfig.BASE_URL instead",
            ReplaceWith("BuildConfig.BASE_URL"),
            DeprecationLevel.WARNING
        )
        const val BASE_URL = "https://main.devapi-pondpedia.com/"
    }
}
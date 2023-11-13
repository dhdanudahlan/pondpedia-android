package com.pondpedia.compose.pondpedia.data.remote.api

import com.pondpedia.compose.pondpedia.data.remote.dto.ai_chat.ChatResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AiChatApi {

    @GET("chat")
    suspend fun getChat(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): List<ChatResponseDto>

    companion object {
        const val BASE_URL = "https://1.1.1.1/v2/"
    }
}
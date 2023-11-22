package com.pondpedia.android.pondpedia.data.local.entity.ai_chat

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.android.pondpedia.data.remote.dto.ai_chat.ChatResponseDto
import com.pondpedia.android.pondpedia.domain.model.ai_chat.ChatResponse

@Entity(tableName = "chat_response_table")
data class ChatResponseEntity(
    @PrimaryKey(autoGenerate = true)
    val chatId: Int = 0,

    val username: String,

    val input: String,

    val response: String
) {
    fun toChatResponse(): ChatResponse {
        return ChatResponse(
            chatId = chatId,
            username = username,
            input = input,
            response = response
        )
    }
    fun toChatResponseDto(): ChatResponseDto {
        return ChatResponseDto(
            chatId = chatId,
            username = username,
            input = input,
            response = response
        )
    }
}

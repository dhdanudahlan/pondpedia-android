package com.pondpedia.compose.pondpedia.data.local.entity.ai_chat

import androidx.room.Entity
import com.pondpedia.compose.pondpedia.data.remote.dto.ai_chat.ChatPromptDto
import com.pondpedia.compose.pondpedia.domain.model.ai_chat.ChatPrompt

@Entity(tableName = "chaat_prompt_table")
data class ChatPromptEntity(
    val chatId: Long = 0,

    val username: String,

    val input: String,
) {
    fun toChatPrompt(): ChatPrompt {
        return ChatPrompt(
            chatId = chatId,
            username = username,
            input = input,
        )
    }
    fun toChatPromptDto(): ChatPromptDto {
        return ChatPromptDto(
            chatId = chatId,
            username = username,
            input = input,
        )
    }
}

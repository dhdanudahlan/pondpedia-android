package com.pondpedia.compose.pondpedia.data.remote.dto.ai_chat

import com.pondpedia.compose.pondpedia.data.local.entity.ai_chat.ChatPromptEntity

data class ChatPromptDto(
    val chatId: Long = 0,

    val username: String,

    val input: String,
) {
    fun toChatPromptEntity(): ChatPromptEntity {
        return ChatPromptEntity(
            chatId = chatId,
            username = username,
            input = input
        )
    }
}

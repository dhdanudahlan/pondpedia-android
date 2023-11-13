package com.pondpedia.compose.pondpedia.domain.model.ai_chat

import com.pondpedia.compose.pondpedia.data.local.entity.ai_chat.ChatResponseEntity

data class ChatResponse(
    val chatId: Int = 0,

    val username: String,

    val input: String,

    val response: String
) {
    fun toChatResponseEntity(): ChatResponseEntity {
        return ChatResponseEntity(
            chatId = chatId,
            username = username,
            input = input,
            response = response
        )
    }
}

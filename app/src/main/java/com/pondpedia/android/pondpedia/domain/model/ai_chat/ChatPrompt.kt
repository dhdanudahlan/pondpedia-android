package com.pondpedia.android.pondpedia.domain.model.ai_chat

import com.pondpedia.android.pondpedia.data.local.entity.ai_chat.ChatPromptEntity

data class ChatPrompt(
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

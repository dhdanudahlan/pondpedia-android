package com.pondpedia.android.pondpedia.presentation.ui.home.menu.screens.ai_chat

import com.pondpedia.android.pondpedia.domain.model.ai_chat.Chat

data class ChatState(
    val chats: List<Chat> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)

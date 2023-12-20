package com.pondpedia.android.pondpedia.presentation.ui.home.menu.screens.ai_chat

import com.pondpedia.android.pondpedia.domain.model.ai_chat.Message

data class ChatState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)

package com.pondpedia.android.pondpedia.domain.use_case.ai_chat

import com.pondpedia.android.pondpedia.data.remote.dto.ai_chat.ChatRequest
import com.pondpedia.android.pondpedia.domain.repository.AiChatRepository

class AddChatUseCase(
    private val repository: AiChatRepository
) {

    suspend operator fun invoke(body: ChatRequest) = repository.sendChat(body)

}
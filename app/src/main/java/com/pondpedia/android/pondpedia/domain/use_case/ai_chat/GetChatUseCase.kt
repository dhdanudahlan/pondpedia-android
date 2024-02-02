package com.pondpedia.android.pondpedia.domain.use_case.ai_chat

import com.pondpedia.android.pondpedia.domain.repository.AiChatRepository

class GetChatUseCase(
    private val repository: AiChatRepository
) {

    suspend operator fun invoke() = repository.getChats()

}
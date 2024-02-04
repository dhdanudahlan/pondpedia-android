package com.pondpedia.android.pondpedia.di

import com.pondpedia.android.pondpedia.core.util.manager.ThreadManager
import com.pondpedia.android.pondpedia.core.util.manager.TokenManager
import com.pondpedia.android.pondpedia.data.local.database.PondPediaDatabase
import com.pondpedia.android.pondpedia.data.remote.api.PondPediaApiService
import com.pondpedia.android.pondpedia.data.repository.AiChatRepositoryImpl
import com.pondpedia.android.pondpedia.domain.repository.AiChatRepository
import com.pondpedia.android.pondpedia.domain.use_case.ai_chat.AddChatUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ai_chat.GetChatUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AiChatModule {

    @Provides
    @Singleton
    fun provideAiChatRepository(
        api: PondPediaApiService,
        db: PondPediaDatabase,
        threadManager: ThreadManager,
        tokenManager: TokenManager
    ): AiChatRepository = AiChatRepositoryImpl(api, db.chatDao, threadManager, tokenManager)


    @Provides
    @Singleton
    fun provideAddChatUseCase(repository: AiChatRepository): AddChatUseCase {
        return AddChatUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetChatUseCase(repository: AiChatRepository): GetChatUseCase {
        return GetChatUseCase(repository)
    }

}
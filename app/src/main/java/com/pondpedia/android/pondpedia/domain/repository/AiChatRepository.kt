package com.pondpedia.android.pondpedia.domain.repository

import com.pondpedia.android.pondpedia.data.local.entity.pond_management.CategoryEntity
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.relations.PondCategoryCrossRefEntity
import com.pondpedia.android.pondpedia.domain.model.ai_chat.ChatResponse
import com.pondpedia.android.pondpedia.domain.model.pond_management.Category
import kotlinx.coroutines.flow.Flow

interface AiChatRepository {

    fun getChatByUsername(
        username: String,
    ): Flow<List<ChatResponse>>

    fun getUsernameList(
    ): Flow<List<String>>


    suspend fun addChatResponse(
        username: String,
        input: String,
        response: String
    )

    suspend fun addCategory(
        category: Category
    )

    suspend fun addPondCategoryCrossRef(
        pondCategoryCrossRef: PondCategoryCrossRefEntity
    )

    suspend fun deletePondById(
        pondId: Long
    )

    suspend fun deleteCategoryName(
        categoryName: String
    )

    suspend fun deletePondCategoryRefById(
        pondId: Long,
        categoryName: String
    )

    suspend fun upsertCategory(categoryEntity: CategoryEntity)
}
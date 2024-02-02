package com.pondpedia.android.pondpedia.data.repository

import com.haroldadmin.cnradapter.NetworkResponse
import com.pondpedia.android.pondpedia.core.util.Resource
import com.pondpedia.android.pondpedia.core.util.manager.ThreadManager
import com.pondpedia.android.pondpedia.core.util.manager.TokenManager
import com.pondpedia.android.pondpedia.data.local.dao.AiChatDao
import com.pondpedia.android.pondpedia.data.local.database.PondPediaDatabase
import com.pondpedia.android.pondpedia.data.local.entity.ai_chat.ChatEntity
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.CategoryEntity
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.relations.PondCategoryCrossRefEntity
import com.pondpedia.android.pondpedia.data.remote.api.PondPediaApiService
import com.pondpedia.android.pondpedia.data.remote.dto.ai_chat.ChatRequest
import com.pondpedia.android.pondpedia.domain.model.ai_chat.Chat
import com.pondpedia.android.pondpedia.domain.model.pond_management.Category
import com.pondpedia.android.pondpedia.domain.repository.AiChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AiChatRepositoryImpl @Inject constructor(
    private val api: PondPediaApiService,
    private val dao: AiChatDao,
    private val threadManager: ThreadManager,
    private val tokenManager: TokenManager,
): AiChatRepository {
    override fun getChatByUsername(username: String): Flow<List<Unit>> {
        return emptyFlow()
    }

    override fun getUsernameList(): Flow<List<String>> {
        return emptyFlow()
    }

    override suspend fun addChatResponse(username: String, input: String, response: String) {

    }

    override suspend fun addCategory(category: Category) {

    }

    override suspend fun addPondCategoryCrossRef(pondCategoryCrossRef: PondCategoryCrossRefEntity) {

    }

    override suspend fun deletePondById(pondId: Long) {

    }

    override suspend fun deleteCategoryName(categoryName: String) {

    }

    override suspend fun deletePondCategoryRefById(pondId: Long, categoryName: String) {

    }

    override suspend fun upsertCategory(categoryEntity: CategoryEntity) {

    }

    override suspend fun sendChat(body: ChatRequest): Resource<Unit> = flow {
        val threadId = threadManager.getThreadId().first().orEmpty()
        val uid = tokenManager.getUserId().first().orEmpty()

        val userEntity = ChatEntity(
            threadId = threadId,
            user = uid,
            message = body.userQuestion,
            time = createTimeStamp()
        )

        //pretending loading through this entity
        val loadingEntity = ChatEntity(
            threadId = threadId,
            user = "asst",
            message = "Menunggu jawaban...",
            time = createTimeStamp()
        )

        dao.insertChat(userEntity)
        dao.insertChat(loadingEntity)

        when(val result = api.sendMessage(threadId, body)) {
            is NetworkResponse.Success -> {
                dao.deleteLatestMessage(threadId)
                val response = result.body.data

                val assistantEntity = ChatEntity(
                    threadId = threadId,
                    user = "asst",
                    message = response.lastAssistantMessage,
                    time = createTimeStamp()
                )

                dao.insertChat(assistantEntity)

                emit(Resource.Success(Unit))
            }

            is NetworkResponse.Error -> {
                dao.deleteLatestMessage(threadId)
                emit(Resource.Error(result.error?.message ?: "Terjadi kesalahan, silahkan coba lagi."))
            }
        }
    }.first()

    override suspend fun getChats(): Flow<List<Chat>> {
        val threadId = threadManager.getThreadId().first().orEmpty()
        return dao.getChat(threadId).map { flow -> flow.map { entity -> entity.toChat() } }
    }

    private fun createTimeStamp(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val currentDate = Date()
        return dateFormat.format(currentDate)
    }

}
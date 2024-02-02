package com.pondpedia.android.pondpedia.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Upsert
import com.pondpedia.android.pondpedia.data.local.entity.ai_chat.ChatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AiChatDao {
    @Upsert
    suspend fun upsertAll(chatResponseList: List<ChatEntity>)
    @Upsert
    suspend fun upsert(chatResponse: ChatEntity)

/*

    @Transaction
    suspend fun refreshAll(chatResponseList: List<ChatResponseEntity>) {
        clearAll()
        upsertAll(chatResponseList)
    }
    @Query("SELECT * FROM chat_response_table")
    fun pagingSource(): PagingSource<Int, ChatResponseEntity>
*/

    @Query("DELETE FROM chat_table")
    fun clearAll()

    @Insert
    suspend fun insertChat(entity: ChatEntity)

    @Query("SELECT * FROM chat_table WHERE threadId = :threadId")
    fun getChat(threadId: String): Flow<List<ChatEntity>>

    //delete latest message
    @Query("DELETE FROM chat_table WHERE chatId = (SELECT chatId FROM chat_table WHERE threadId = :threadId ORDER BY chatId DESC LIMIT 1)")
    suspend fun deleteLatestMessage(threadId: String)
}
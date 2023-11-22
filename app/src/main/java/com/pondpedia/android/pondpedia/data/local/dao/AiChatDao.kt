package com.pondpedia.android.pondpedia.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.pondpedia.android.pondpedia.data.local.entity.ai_chat.ChatResponseEntity

@Dao
interface AiChatDao {
    @Upsert
    suspend fun upsertAll(chatResponseList: List<ChatResponseEntity>)
    @Upsert
    suspend fun upsert(chatResponse: ChatResponseEntity)

/*

    @Transaction
    suspend fun refreshAll(chatResponseList: List<ChatResponseEntity>) {
        clearAll()
        upsertAll(chatResponseList)
    }
    @Query("SELECT * FROM chat_response_table")
    fun pagingSource(): PagingSource<Int, ChatResponseEntity>
*/

    @Query("DELETE FROM chat_response_table")
    fun clearAll()
}
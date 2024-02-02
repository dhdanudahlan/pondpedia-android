package com.pondpedia.android.pondpedia.data.local.entity.ai_chat

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.android.pondpedia.domain.model.ai_chat.Chat

@Entity(tableName = "chat_table")
data class ChatEntity(
    @PrimaryKey(autoGenerate = true)
    val chatId: Int = 0,

    val threadId: String,

    //uid for the user and asst for the assistant
    val user: String,

    val message: String,

    val time: String
) {

    fun toChat(): Chat {
        return Chat(
            text = message,
            formattedTime = time,
            username = user
        )
    }

}

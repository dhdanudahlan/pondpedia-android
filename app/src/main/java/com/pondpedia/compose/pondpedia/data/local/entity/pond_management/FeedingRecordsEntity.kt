package com.pondpedia.compose.pondpedia.data.local.entity.pond_management

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.compose.pondpedia.domain.model.pond_management.FeedingRecords

@Entity(tableName = "feeding_records_table")
data class FeedingRecordsEntity(

    @PrimaryKey(autoGenerate = true)
    val recordId: Long = 0,

    val date: String,

    val quantity: Float,

    val note: String,

    val feedId: Long,

    val pondId: Long
) {
    fun toFeedingRecords(): FeedingRecords {
        return FeedingRecords(
            recordId = recordId,
            date = date,
            quantity = quantity.toString(),
            note = note,
            feedId = feedId,
            pondId = pondId
        )
    }
}

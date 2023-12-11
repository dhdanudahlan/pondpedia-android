package com.pondpedia.android.pondpedia.data.local.entity.pond_management.new_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.android.pondpedia.domain.model.pond_management.new_model.FeedingRecords

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
            quantity = quantity,
            note = note,
            feedId = feedId,
            pondId = pondId
        )
    }
}

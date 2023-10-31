package com.pondpedia.compose.pondpedia.domain.model.pond_management

import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.FeedingRecordsEntity

data class FeedingRecords(

    val recordId: Long,

    val date: String,

    val quantity: Int,

    val note: String,

    val feedId: Long,

    val pondId: Long
) {
    fun toFeedingRecordsEntity(): FeedingRecordsEntity {
        return FeedingRecordsEntity(
            recordId = recordId,
            date = date,
            quantity = quantity,
            note = note,
            feedId = feedId,
            pondId = pondId
        )
    }
}

package com.pondpedia.android.pondpedia.domain.model.pond_management.new_model

import com.pondpedia.android.pondpedia.data.local.entity.pond_management.new_model.FeedingRecordsEntity

data class FeedingRecords(

    val recordId: Long,

    val date: String,

    val quantity: Float,

    val note: String,

    val feedId: Long,

    val pondId: Long
) {
    fun toFeedingRecordsEntity(): FeedingRecordsEntity {
        return FeedingRecordsEntity(
            recordId = recordId,
            date = date,
            quantity = quantity.toFloat(),
            note = note,
            feedId = feedId,
            pondId = pondId
        )
    }
}

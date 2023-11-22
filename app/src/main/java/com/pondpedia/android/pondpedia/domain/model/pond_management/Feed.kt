package com.pondpedia.android.pondpedia.domain.model.pond_management

import com.pondpedia.android.pondpedia.data.local.entity.pond_management.FeedEntity


data class Feed(
    val feedId: Long,

    val date: String,

    val origin: String,

    val name: String,

    val nutritionalValue: String,
) {
    fun toFeedEntity(): FeedEntity {
        return FeedEntity(
            feedId = feedId,
            date = date,
            origin = origin,
            name = name,
            nutritionalValue = nutritionalValue
        )
    }
}

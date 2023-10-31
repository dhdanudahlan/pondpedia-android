package com.pondpedia.compose.pondpedia.data.local.entity.pond_management

import androidx.room.Entity
import com.pondpedia.compose.pondpedia.domain.model.pond_management.Feed

@Entity(tableName = "feed_table")
data class FeedEntity(
    val feedId: Long = 0,

    val date: String,

    val origin: String,

    val name: String,

    val nutritionalValue: String,
) {
    fun toFeed(): Feed {
        return Feed(
            feedId = feedId,
            date = date,
            origin = origin,
            name = name,
            nutritionalValue = nutritionalValue
        )
    }
}

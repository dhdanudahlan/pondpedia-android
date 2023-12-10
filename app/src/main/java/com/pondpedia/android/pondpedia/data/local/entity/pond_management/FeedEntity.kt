package com.pondpedia.android.pondpedia.data.local.entity.pond_management

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.android.pondpedia.domain.model.pond_management.Feed
import java.time.LocalDateTime

@Entity(tableName = "feed_table")
data class FeedEntity(
    @PrimaryKey(autoGenerate = true)
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

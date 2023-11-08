package com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.FeedingRecordsEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.PondEntity

data class PondWithFeedingRecordsEntity(
    @Embedded val pond: PondEntity,
    @Relation(
        parentColumn = "pondId",
        entityColumn = "pondId"
    )

    val feedingRecords: List<FeedingRecordsEntity>
)

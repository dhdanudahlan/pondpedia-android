package com.pondpedia.android.pondpedia.data.local.entity.pond_management.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.new_model.FeedingRecordsEntity
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.PondEntity

data class PondWithFeedingRecordsEntity(
    @Embedded val pond: PondEntity,
    @Relation(
        parentColumn = "pondId",
        entityColumn = "pondId"
    )

    val feedingRecords: List<FeedingRecordsEntity>
)

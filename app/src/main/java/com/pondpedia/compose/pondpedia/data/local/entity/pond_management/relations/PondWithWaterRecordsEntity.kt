package com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.PondEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.WaterRecordsEntity

data class PondWithWaterRecordsEntity(
    @Embedded val pond: PondEntity,
    @Relation(
        parentColumn = "pondId",
        entityColumn = "pondId"
    )

    val waterRecords: List<WaterRecordsEntity>
)

package com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.PondEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.PondRecordsEntity

data class PondWithPondRecordsEntity(
    @Embedded val pond: PondEntity,
    @Relation(
        parentColumn = "pondId",
        entityColumn = "pondId"
    )

    val pondRecords: List<PondRecordsEntity>
)

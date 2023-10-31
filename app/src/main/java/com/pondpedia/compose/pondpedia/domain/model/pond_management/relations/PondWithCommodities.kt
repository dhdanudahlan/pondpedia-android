package com.pondpedia.compose.pondpedia.domain.model.pond_management.relations
import androidx.room.Embedded
import androidx.room.Relation
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CommodityEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.PondEntity

data class PondWithCommodities(
    @Embedded val pond: PondEntity,
    @Relation(
        parentColumn = "pondId",
        entityColumn = "pondId"
    )

    val commodities: List<CommodityEntity>
)

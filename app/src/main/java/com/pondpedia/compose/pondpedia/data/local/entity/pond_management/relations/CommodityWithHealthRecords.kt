package com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CommodityEntity
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CommodityHealthRecordsEntity

data class CommodityWithHealthRecords(
    @Embedded val commodity: CommodityEntity,
    @Relation(
        parentColumn = "commodityId",
        entityColumn = "commodityId"
    )
    val growthRecords: List<CommodityHealthRecordsEntity>
)
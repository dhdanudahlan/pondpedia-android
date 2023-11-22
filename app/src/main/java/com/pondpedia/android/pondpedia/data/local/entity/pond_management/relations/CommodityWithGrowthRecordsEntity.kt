package com.pondpedia.android.pondpedia.data.local.entity.pond_management.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.pondpedia.android.pondpedia.core.util.DateGenerator
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.CommodityEntity
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.CommodityGrowthRecordsEntity

data class CommodityWithGrowthRecordsEntity(
    @Embedded val commodity: CommodityEntity = CommodityEntity(date = DateGenerator.getCurrentDateTime(), origin = "", quantity = 0, name = "", scientificName = "", pondId = 0),
    @Relation(
        parentColumn = "commodityId",
        entityColumn = "commodityId"
    )
    val growthRecords: List<CommodityGrowthRecordsEntity> = emptyList()
)
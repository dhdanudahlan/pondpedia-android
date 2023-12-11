package com.pondpedia.android.pondpedia.domain.model.pond_management

import com.pondpedia.android.pondpedia.data.local.entity.pond_management.CommodityGrowthRecordsEntity
import java.time.LocalDateTime

data class CommodityGrowthRecords(

    val recordId: Long,

    val date: String,

    val age: Int,

    val length: Int,

    val weight: Int,

    val note: String,

    val commodityId: Long,
) {
    fun toCommodityGrowthRecordsEntity(): CommodityGrowthRecordsEntity {
        return CommodityGrowthRecordsEntity(
            recordId = recordId,
            date = date,
            age = age,
            length = length,
            weight = weight,
            note = note,
            commodityId = commodityId
        )
    }
}

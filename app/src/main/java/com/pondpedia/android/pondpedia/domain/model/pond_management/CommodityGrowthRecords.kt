package com.pondpedia.android.pondpedia.domain.model.pond_management

import com.pondpedia.android.pondpedia.data.local.entity.pond_management.CommodityGrowthRecordsEntity
import java.time.LocalDateTime

data class CommodityGrowthRecords(

    val recordId: Long,

    val date: String,

    val age: String,

    val length: String,

    val weight: String,

    val note: String,

    val commodityId: Long,
) {
    fun toCommodityGrowthRecordsEntity(): CommodityGrowthRecordsEntity {
        return CommodityGrowthRecordsEntity(
            recordId = recordId,
            date = date,
            age = age.toFloat().toInt(),
            length = length.toFloat().toInt(),
            weight = weight.toFloat().toInt(),
            note = note,
            commodityId = commodityId
        )
    }
}

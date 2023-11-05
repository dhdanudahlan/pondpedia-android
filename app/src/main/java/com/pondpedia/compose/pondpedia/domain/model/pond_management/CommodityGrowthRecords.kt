package com.pondpedia.compose.pondpedia.domain.model.pond_management

import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CommodityGrowthRecordsEntity

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
            age = age.toInt(),
            length = length.toInt(),
            weight = weight.toInt(),
            note = note,
            commodityId = commodityId
        )
    }
}

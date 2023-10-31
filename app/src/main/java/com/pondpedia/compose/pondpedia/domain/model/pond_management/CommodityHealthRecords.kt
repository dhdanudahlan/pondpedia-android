package com.pondpedia.compose.pondpedia.domain.model.pond_management

import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CommodityHealthRecordsEntity

data class CommodityHealthRecords(

    val recordId: Long,

    val date: String,

    val death: Int,

    val indicator: String,

    val action: String,

    val note: String,

    val commodityId: Long,
) {
    fun toCommodityHealthRecordsEntity(): CommodityHealthRecordsEntity {
        return CommodityHealthRecordsEntity(
            recordId = recordId,
            date = date,
            death = death,
            indicator = indicator,
            action = action,
            note = note,
            commodityId = commodityId
        )
    }
}

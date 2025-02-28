package com.pondpedia.android.pondpedia.domain.model.pond_management

import com.pondpedia.android.pondpedia.data.local.entity.pond_management.CommodityHealthRecordsEntity
import java.time.LocalDateTime

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
            death = death.toFloat().toInt(),
            indicator = indicator,
            action = action,
            note = note,
            commodityId = commodityId
        )
    }
}

package com.pondpedia.compose.pondpedia.data.local.entity.pond_management

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.compose.pondpedia.domain.model.pond_management.CommodityHealthRecords

@Entity(tableName = "commodity_health_records_table")
data class CommodityHealthRecordsEntity(

    @PrimaryKey(autoGenerate = true)
    val recordId: Long = 0,

    val date: String,

    val death: Int,

    val indicator: String,

    val action: String,

    val note: String,

    val commodityId: Long,
) {
    fun toCommodityHealthRecords(): CommodityHealthRecords {
        return CommodityHealthRecords(
            recordId = recordId,
            date = date,
            death = death.toString(),
            indicator = indicator,
            action = action,
            note = note,
            commodityId = commodityId
        )
    }
}

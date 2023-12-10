package com.pondpedia.android.pondpedia.data.local.entity.pond_management

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.android.pondpedia.domain.model.pond_management.CommodityHealthRecords
import java.time.LocalDateTime

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

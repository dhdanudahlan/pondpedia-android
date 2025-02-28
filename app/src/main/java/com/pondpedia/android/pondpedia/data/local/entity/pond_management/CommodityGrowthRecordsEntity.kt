package com.pondpedia.android.pondpedia.data.local.entity.pond_management

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.android.pondpedia.domain.model.pond_management.CommodityGrowthRecords
import java.time.LocalDateTime

@Entity(tableName = "commodity_growth_records_table")
data class CommodityGrowthRecordsEntity(

    @PrimaryKey(autoGenerate = true)
    val recordId: Long = 0,

    val date: String,

    val age: Int,

    val length: Int,

    val weight: Int,

    val note: String,

    val commodityId: Long,

    ){
    fun toCommodityGrowthRecords(): CommodityGrowthRecords {
        return CommodityGrowthRecords(
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

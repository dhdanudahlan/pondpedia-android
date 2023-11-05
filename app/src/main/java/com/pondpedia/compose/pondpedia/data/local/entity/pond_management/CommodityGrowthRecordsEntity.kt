package com.pondpedia.compose.pondpedia.data.local.entity.pond_management

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.compose.pondpedia.domain.model.pond_management.CommodityGrowthRecords

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
            age = age.toString(),
            length = length.toString(),
            weight = weight.toString(),
            note = note,
            commodityId = commodityId
        )
    }
}

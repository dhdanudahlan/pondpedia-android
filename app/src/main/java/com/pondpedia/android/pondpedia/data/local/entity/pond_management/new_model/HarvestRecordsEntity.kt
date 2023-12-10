package com.pondpedia.android.pondpedia.data.local.entity.pond_management.new_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.android.pondpedia.domain.model.pond_management.new_model.HarvestRecords

@Entity(tableName = "harvest_records")
data class HarvestRecordsEntity(
    @PrimaryKey(autoGenerate = true)
    val recordId: Long = 0,

    val date: String,

    val harvestType: String,  // Total, Parsial, Gagal

    val weightTotal: Float,  // kg

    val size: Int,  // Ekor/kg

    val priceKg: Int,  // Optional

    val customer: String,  // Optional

    val note: String = "",

    val commodityId: Long = 0,

    val pondId: Long = 0,
) {
    fun toHarvestRecords(): HarvestRecords {
        return HarvestRecords(
            recordId = recordId,
            date = date,
            harvestType = harvestType,
            weightTotal = weightTotal,
            size = size,
            priceKg = priceKg,
            customer = customer,
            note = note,
            commodityId = commodityId,
            pondId = pondId
        )
    }
}

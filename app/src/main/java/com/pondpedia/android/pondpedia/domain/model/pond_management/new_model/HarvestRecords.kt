package com.pondpedia.android.pondpedia.domain.model.pond_management.new_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.new_model.HarvestRecordsEntity
import java.time.LocalDateTime


data class HarvestRecords(

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
    fun toHarvestRecordsEntity(): HarvestRecordsEntity {
        return HarvestRecordsEntity(
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

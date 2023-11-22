package com.pondpedia.android.pondpedia.domain.model.pond_management

import com.pondpedia.android.pondpedia.data.local.entity.pond_management.WaterRecordsEntity

data class WaterRecords(

    val recordId: Long,

    val date: String,

    val level: String,

    val quality: String,

    val color: String,

    val note: String,

    val pondId: Long,
) {
    fun toWaterRecordsEntity(): WaterRecordsEntity {
        return WaterRecordsEntity(
            recordId = recordId,
            date = date,
            level = level.toFloat().toInt(),
            quality = quality,
            color = color,
            note = note,
            pondId = pondId
        )
    }
}

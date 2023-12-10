package com.pondpedia.android.pondpedia.data.local.entity.pond_management

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.android.pondpedia.domain.model.pond_management.WaterRecords
import java.time.LocalDateTime

@Entity(tableName = "water_records_table")
data class WaterRecordsEntity(

    @PrimaryKey(autoGenerate = true)
    val recordId: Long = 0,

    val date: String,

    val level: Int,

    val quality: String,

    val color: String,

    val note: String,

    val pondId: Long,
) {
    fun toWaterRecords(): WaterRecords {
        return WaterRecords(
            recordId = recordId,
            date = date,
            level = level.toString(),
            quality = quality,
            color = color,
            note = note,
            pondId = pondId
        )
    }
}

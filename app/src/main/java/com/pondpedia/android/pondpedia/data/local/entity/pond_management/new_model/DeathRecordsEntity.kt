package com.pondpedia.android.pondpedia.data.local.entity.pond_management.new_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.android.pondpedia.domain.model.pond_management.new_model.DeathRecords

@Entity(tableName = "sampling_records")
data class DeathRecordsEntity(
    @PrimaryKey(autoGenerate = true)
    val recordId: Long = 0,

    val date: String,

    val weightTotal: Float,  // kg
    
    val deathCount: Int, // ekor

    val note: String = "",

    val commodityId: Long = 0,

    val pondId: Long = 0,
) {
    fun toDeathRecords(): DeathRecords {
        return DeathRecords(
            recordId = recordId,
            date = date,
            weightTotal = weightTotal,
            deathCount = deathCount,
            note = note,
            commodityId = commodityId,
            pondId = pondId
        )
    }
}

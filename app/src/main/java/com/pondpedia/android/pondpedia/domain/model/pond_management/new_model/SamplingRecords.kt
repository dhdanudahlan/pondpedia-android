package com.pondpedia.android.pondpedia.domain.model.pond_management.new_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.new_model.SamplingRecordsEntity
import java.time.LocalDateTime


data class SamplingRecords(

    val recordId: Long = 0,

    val date: String,

    val meanBodyWeight: Float, // g

    val meanBodyLength: Float? = null, // cm

    val note: String = "",

    val commodityId: Long = 0,

    val pondId: Long = 0,
) {
    fun toSamplingRecordsEntity(): SamplingRecordsEntity {
        return SamplingRecordsEntity(
            recordId = recordId,
            date = date,
            meanBodyWeight = meanBodyWeight,
            meanBodyLength = meanBodyLength,
            note = note,
            commodityId = commodityId,
            pondId = pondId
        )
    }
}

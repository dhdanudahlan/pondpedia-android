package com.pondpedia.android.pondpedia.domain.model.pond_management.new_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.new_model.TreatmentRecordsEntity
import java.time.LocalDateTime


data class TreatmentRecords(

    val recordId: Long = 0,

    val date: String,

    val treatment: String,

    val note: String = "",

    val pondId: Long = 0,
) {
    fun toTreatmentRecordsEntity(): TreatmentRecordsEntity {
        return TreatmentRecordsEntity(
            recordId = recordId,
            date = date,
            treatment = treatment,
            note = note,
            pondId = pondId
        )
    }
}

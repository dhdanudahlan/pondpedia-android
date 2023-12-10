package com.pondpedia.android.pondpedia.data.local.entity.pond_management.new_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.android.pondpedia.domain.model.pond_management.new_model.TreatmentRecords

@Entity(tableName = "treatment_records")
data class TreatmentRecordsEntity(
    @PrimaryKey(autoGenerate = true)
    val recordId: Long = 0,

    val date: String,

    val treatment: String,

    val note: String = "",

    val pondId: Long = 0,
) {
    fun toTreatmentRecords(): TreatmentRecords {
        return TreatmentRecords(
            recordId = recordId,
            date = date,
            treatment = treatment,
            note = note,
            pondId = pondId
        )
    }
}

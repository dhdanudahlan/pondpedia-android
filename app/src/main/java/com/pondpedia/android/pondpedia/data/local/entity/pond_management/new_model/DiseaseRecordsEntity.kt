package com.pondpedia.android.pondpedia.data.local.entity.pond_management.new_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.android.pondpedia.domain.model.pond_management.new_model.DiseaseRecords

@Entity(tableName = "sampling_records")
data class DiseaseRecordsEntity(
    @PrimaryKey(autoGenerate = true)
    val recordId: Long = 0,

    val date: String,

    val diseaseName: String,  //

    val note: String = "",

    val commodityId: Long = 0,

    val pondId: Long = 0,
) {
    fun toDiseaseRecords(): DiseaseRecords {
        return DiseaseRecords(
            recordId = recordId,
            date = date,
            diseaseName = diseaseName,
            note = note,
            commodityId = commodityId,
            pondId = pondId
        )
    }
}

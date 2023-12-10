package com.pondpedia.android.pondpedia.domain.model.pond_management

import com.pondpedia.android.pondpedia.data.local.entity.pond_management.PondRecordsEntity
import java.time.LocalDateTime

data class PondRecords(

    val recordId: Long = 0,

    val date: String,

    val cycle: String,

    val note: String,

    val pondId: Long
) {
    fun toPondRecordsEntity(): PondRecordsEntity {
        return PondRecordsEntity(
            recordId = recordId,
            date = date,
            cycle = cycle.toFloat().toInt(),
            note = note,
            pondId = pondId
        )
    }
}

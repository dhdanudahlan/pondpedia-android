package com.pondpedia.compose.pondpedia.domain.model.pond_management

import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.PondRecordsEntity

data class PondRecords(

    val recordId: Long,

    val date: String,

    val cycle: Int,

    val note: String,

    val pondId: Long
) {
    fun toPondRecordsEntity(): PondRecordsEntity {
        return PondRecordsEntity(
            recordId = recordId,
            date = date,
            cycle = cycle,
            note = note,
            pondId = pondId
        )
    }
}

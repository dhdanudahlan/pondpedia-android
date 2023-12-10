package com.pondpedia.android.pondpedia.data.local.entity.pond_management

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.android.pondpedia.domain.model.pond_management.PondRecords
import java.time.LocalDateTime

@Entity(tableName = "pond_records_table")
data class PondRecordsEntity(

    @PrimaryKey(autoGenerate = true)
    val recordId: Long = 0,

    val date: String,

    val cycle: Int,

    val note: String,

    val pondId: Long
) {
    fun toPondRecords(): PondRecords {
        return PondRecords(
            recordId = recordId,
            date = date,
            cycle = cycle.toString(),
            note = note,
            pondId = pondId
        )
    }
}

package com.pondpedia.compose.pondpedia.data.local.entity.pond_management

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.compose.pondpedia.domain.model.pond_management.Pond

@Entity(tableName = "pond_table")
data class PondEntity(

    @PrimaryKey(autoGenerate = true)
    val pondId: Long = 0,

    val name: String,

    val area: Float,

    val depth: Float,

    val pondType: String,

    val waterType: String,

    val location: String,

    val description: String,

    val farmerId: Long,
) {
    fun toPond(): Pond {
        return Pond(
            pondId = pondId,
            name = name,
            area = area,
            depth = depth,
            pondType = pondType,
            waterType = waterType,
            location = location,
            description = description,
            farmerId = farmerId
        )
    }
}

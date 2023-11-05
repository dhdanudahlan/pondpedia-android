package com.pondpedia.compose.pondpedia.data.local.entity.pond_management

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.compose.pondpedia.core.util.DateGenerator
import com.pondpedia.compose.pondpedia.domain.model.pond_management.Pond

@Entity(tableName = "pond_table")
data class PondEntity(

    @PrimaryKey(autoGenerate = true)
    val pondId: Long = 0,

    val name: String,

    val area: Int,

    val depth: Int,

    val pondType: String,

    val waterType: String,

    val location: String,

    val description: String,

    val createdDate: String = DateGenerator.getCurrentDateTime(),

    val updatedDate: String = DateGenerator.getCurrentDateTime(),

    val farmerId: Long,
) {
    fun toPond(): Pond {
        return Pond(
            pondId = pondId,
            name = name,
            area = area.toString(),
            depth = depth.toString(),
            pondType = pondType,
            waterType = waterType,
            location = location,
            description = description,
            createdDate = createdDate,
            updatedDate = updatedDate,
            farmerId = farmerId
        )
    }
}

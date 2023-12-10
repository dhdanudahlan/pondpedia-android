package com.pondpedia.android.pondpedia.domain.model.pond_management

import com.pondpedia.android.pondpedia.core.util.DateGenerator
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.PondEntity
import java.time.LocalDateTime

data class Pond(

    val pondId: Long = 0,

    val name: String = "",

    val area: String = "",

    val depth: String = "",

    val pondType: String = "",

    val waterType: String = "",

    val location: String = "",

    val description: String = "",

    val createdDate: String = DateGenerator.getCurrentDateTime(),

    val updatedDate: String = DateGenerator.getCurrentDateTime(),

    val farmerId: Long = 0,
) {
    fun toPondEntity(): PondEntity {
        return PondEntity(
            pondId = pondId,
            name = name,
            area = area.toFloat().toInt(),
            depth = depth.toFloat().toInt(),
            pondType = pondType,
            waterType = waterType,
            location = location,
            description = description,
            createdDate = createdDate,
            updatedDate = updatedDate,
            farmerId = farmerId.toLong()
        )
    }
}

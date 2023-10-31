package com.pondpedia.compose.pondpedia.domain.model.pond_management

import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.PondEntity

data class Pond(

    val pondId: Long,

    val name: String,

    val area: Float,

    val depth: Float,

    val pondType: String,

    val waterType: String,

    val location: String,

    val description: String,

    val farmerId: Long,
) {
    fun toPondEntity(): PondEntity {
        return PondEntity(
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

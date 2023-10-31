package com.pondpedia.compose.pondpedia.domain.model.pond_management

import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CommodityEntity

data class Commodity(

    val commodityId: Long,

    val date: String,

    val origin: String,

    val quantity: Float,

    val name: String,

    val scientificName: String,

    val pondId: Long,
) {
    fun toCommodityEntity(): CommodityEntity {
        return CommodityEntity(
            commodityId = commodityId,
            date = date,
            origin = origin,
            quantity = quantity,
            name = name,
            scientificName = scientificName,
            pondId = pondId
        )
    }
}

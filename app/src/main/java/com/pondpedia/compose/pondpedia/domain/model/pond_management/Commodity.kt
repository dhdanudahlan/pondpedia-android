package com.pondpedia.compose.pondpedia.domain.model.pond_management

import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CommodityEntity

data class Commodity(

    val commodityId: Long,

    val date: String,

    val origin: String,

    val quantity: String,

    val name: String,

    val scientificName: String,

    val pondId: Long,
) {
    fun toCommodityEntity(): CommodityEntity {
        return CommodityEntity(
            commodityId = commodityId,
            date = date,
            origin = origin,
            quantity = quantity.toInt(),
            name = name,
            scientificName = scientificName,
            pondId = pondId
        )
    }
}

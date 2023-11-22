package com.pondpedia.android.pondpedia.domain.model.pond_management

import com.pondpedia.android.pondpedia.data.local.entity.pond_management.CommodityEntity

data class Commodity(

    val commodityId: Long = 0,

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
            quantity = quantity.toFloat().toInt(),
            name = name,
            scientificName = scientificName,
            pondId = pondId
        )
    }
}

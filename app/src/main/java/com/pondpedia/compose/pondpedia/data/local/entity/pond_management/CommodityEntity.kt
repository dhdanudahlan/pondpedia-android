package com.pondpedia.compose.pondpedia.data.local.entity.pond_management

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.compose.pondpedia.domain.model.pond_management.Commodity

@Entity(tableName = "commodity_table")
data class CommodityEntity(

    @PrimaryKey(autoGenerate = true)
    val commodityId: Long = 0,

    val date: String,

    val origin: String,

    val quantity: Float,

    val name: String,

    val scientificName: String,

    val pondId: Long,
) {
    fun toCommodity(): Commodity {
        return Commodity(
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


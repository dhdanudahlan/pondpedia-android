package com.pondpedia.compose.pondpedia.domain.model.auth

import com.pondpedia.compose.pondpedia.data.local.entity.auth.FarmerEntity

data class Farmer(
    val farmerId: Long,

    val name: String,

    val phoneNumber: String,

    val email: String,

    val preferences: String
) {
    fun toFarmerEntity(): FarmerEntity {
        return FarmerEntity(
            farmerId = farmerId,
            name = name,
            phoneNumber = phoneNumber,
            email = email,
            preferences = preferences
        )
    }
}

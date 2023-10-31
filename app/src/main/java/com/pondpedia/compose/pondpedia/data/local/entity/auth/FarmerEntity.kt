package com.pondpedia.compose.pondpedia.data.local.entity.auth

import com.pondpedia.compose.pondpedia.domain.model.auth.Farmer

data class FarmerEntity(
    val farmerId: Long = 0,

    val name: String,

    val phoneNumber: String,

    val email: String,

    val preferences: String
) {
    fun toFarmer(): Farmer {
        return Farmer(
            farmerId = farmerId,
            name = name,
            phoneNumber = phoneNumber,
            email = email,
            preferences = preferences
        )
    }
}

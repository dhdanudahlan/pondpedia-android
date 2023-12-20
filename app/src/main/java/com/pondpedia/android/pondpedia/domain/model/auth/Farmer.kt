package com.pondpedia.android.pondpedia.domain.model.auth

import com.pondpedia.android.pondpedia.data.local.entity.auth.FarmerEntity

data class Farmer(
    val farmerId: Long,

    val name: String,

    val phoneNumber: String,

    val email: String,

    val occupation: String,

    val informationSource: String,

    val photoUrl: String,

    val preferences: String
) {
    fun toFarmerEntity(): FarmerEntity {
        return FarmerEntity(
            farmerId = farmerId,
            name = name,
            phoneNumber = phoneNumber,
            email = email,
            occupation = occupation,
            informationSource = informationSource,
            photoUrl = photoUrl,
            preferences = preferences
        )
    }
}

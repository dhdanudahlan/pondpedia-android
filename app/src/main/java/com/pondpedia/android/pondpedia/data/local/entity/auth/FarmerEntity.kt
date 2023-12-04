package com.pondpedia.android.pondpedia.data.local.entity.auth

import com.pondpedia.android.pondpedia.domain.model.auth.Farmer

data class FarmerEntity(
    val farmerId: Long = 0,

    val name: String,

    val phoneNumber: String,

    val email: String,

    val password: String,

    val occupation: String,

    val informationSource: String,

    val photoUrl: String,

    val firabaseId: String,

    val preferences: String
) {
    fun toFarmer(): Farmer {
        return Farmer(
            farmerId = farmerId,
            name = name,
            phoneNumber = phoneNumber,
            email = email,
            occupation = occupation,
            informationSource = informationSource,
            photoUrl = photoUrl,
            firabaseId = firabaseId,
            preferences = preferences
        )
    }
}

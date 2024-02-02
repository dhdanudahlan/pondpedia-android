package com.pondpedia.android.pondpedia.domain.model.auth

import com.google.gson.annotations.SerializedName
import com.pondpedia.android.pondpedia.data.local.entity.auth.FarmerEntity

data class Farmer(
    val id: String,
    val assistantId: String,
    val name: String,
    val username: String,
    val phoneNumber: String,
    val profilePicture: Any?,
    val occupation: String,
    val usesSocialLogin: Boolean,
    val education: Any?,
    val gender: Any?,
    val birthdate: String,
    val birthplace: Any?,
    val domicile: Any?,
    val bio: Any?,
    val roles: List<String>,
    val email: String,
    val verified: Boolean,
    val loginAttempts: Long,
) {
    fun toFarmerEntity(): FarmerEntity {
        return FarmerEntity(
            id = id,
            assistantId = assistantId,
            name = name,
            username = username,
            phoneNumber = phoneNumber,
            profilePicture = profilePicture,
            occupation = occupation,
            usesSocialLogin = usesSocialLogin,
            education = education,
            gender = gender,
            birthdate = birthdate,
            birthplace = birthplace,
            domicile = domicile,
            bio = bio,
            roles = roles,
            email = email,
            verified = verified,
            loginAttempts = loginAttempts
        )
    }
}

package com.pondpedia.android.pondpedia.data.remote.dto.user

import com.google.gson.annotations.SerializedName
import com.pondpedia.android.pondpedia.domain.model.auth.Farmer


data class FarmerResponse(
    val id: String,
    val assistantId: String,
    val name: String,
    val username: String,
    @field:SerializedName("phone_number")
    val phoneNumber: String,
    @field:SerializedName("profile_picture")
    val profilePicture: Any?,
    val occupation: String,
    @field:SerializedName("uses_social_login")
    val usesSocialLogin: Boolean,
    val education: Any?,
    val gender: Any?,
    val birthdate: String,
    val birthplace: Any?,
    val domicile: Any?,
    val bio: Any?,
    val roles: List<String>,
    val email: String,
    @field:SerializedName("_verified")
    val verified: Boolean,
    val loginAttempts: Long,
) {
    fun toFarmer() = Farmer(
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

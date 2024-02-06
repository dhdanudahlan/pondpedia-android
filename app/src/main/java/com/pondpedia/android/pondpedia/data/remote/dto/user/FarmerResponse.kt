package com.pondpedia.android.pondpedia.data.remote.dto.user

import com.google.gson.annotations.SerializedName
import com.pondpedia.android.pondpedia.domain.model.auth.Farmer


data class FarmerResponse(
    val id: String = "",
    val assistantId: String = "",
    val name: String = "",
    val username: String = "",
    @field:SerializedName("phone_number")
    val phoneNumber: String = "",
    @field:SerializedName("profile_picture")
    val profilePicture: String? = "",
    val occupation: String = "",
    @field:SerializedName("uses_social_login")
    val usesSocialLogin: Boolean = false,
    val education: String? = "",
    val gender: String? = "",
    val birthdate: String = "",
    val birthplace: String? = "",
    val domicile: String? = "",
    val bio: String? = "",
    val roles: List<String> = emptyList(),
    val email: String = "",
    @field:SerializedName("_verified")
    val verified: Boolean = false,
    val loginAttempts: Long = 0,
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

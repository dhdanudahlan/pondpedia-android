package com.pondpedia.android.pondpedia.data.local.entity.auth

import com.pondpedia.android.pondpedia.domain.model.auth.Farmer

data class FarmerEntity(
    val id: String = "",
    val assistantId: String = "",
    val name: String = "",
    val username: String = "",
    val phoneNumber: String = "",
    val profilePicture: String? = "",
    val occupation: String = "",
    val usesSocialLogin: Boolean = false,
    val education: String? = "",
    val gender: String? = "",
    val birthdate: String = "",
    val birthplace: String? = "",
    val domicile: String? = "",
    val bio: String? = "",
    val roles: List<String> = emptyList(),
    val email: String = "",
    val verified: Boolean = false,
    val loginAttempts: Long = 0,
) {
    fun toFarmer(): Farmer {
        return Farmer(
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

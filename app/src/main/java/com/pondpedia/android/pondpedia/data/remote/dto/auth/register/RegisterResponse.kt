package com.pondpedia.android.pondpedia.data.remote.dto.auth.register

import com.pondpedia.android.pondpedia.data.remote.dto.user.FarmerResponse

data class RegisterResponse(
    val message: String,
    val user: FarmerResponse
)

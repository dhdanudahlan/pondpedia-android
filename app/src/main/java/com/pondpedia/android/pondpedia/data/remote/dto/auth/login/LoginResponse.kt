package com.pondpedia.android.pondpedia.data.remote.dto.auth.login

import com.pondpedia.android.pondpedia.data.remote.dto.user.FarmerResponse

data class LoginResponse(
    val exp: Int,
    val message: String,
    val token: String,
    val user: FarmerResponse
)

data class LoginErrorResponse(
    val errors: List<Error>
)

data class Error(
    val message: String
)
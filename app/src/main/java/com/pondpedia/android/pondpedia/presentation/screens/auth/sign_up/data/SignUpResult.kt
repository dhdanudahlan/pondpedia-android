package com.pondpedia.android.pondpedia.presentation.screens.auth.sign_up.data

import com.pondpedia.android.pondpedia.domain.model.auth.Farmer


data class SignUpResult(
    val data: Farmer?,
    val errorMessage: String?
)

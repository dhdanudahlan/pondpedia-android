package com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.data

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null,
)

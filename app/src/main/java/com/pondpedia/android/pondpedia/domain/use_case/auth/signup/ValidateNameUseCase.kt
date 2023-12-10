package com.pondpedia.android.pondpedia.domain.use_case.auth.signup

import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.data.ValidationResult

class ValidateNameUseCase {

    operator fun invoke(name: String): ValidationResult {
        if (name.length < 2) {
            return ValidationResult(
                successful = false,
                errorMessage = "Masukkan nama anda"
            )
        }
        return ValidationResult(
            successful = true
        )
    }


}
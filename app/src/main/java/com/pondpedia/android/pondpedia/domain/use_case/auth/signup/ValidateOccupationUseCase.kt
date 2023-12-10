package com.pondpedia.android.pondpedia.domain.use_case.auth.signup

import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.data.ValidationResult

class ValidateOccupationUseCase {

    operator fun invoke(occupation: String): ValidationResult {
        if (occupation.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Pekerjaan tidak boleh kosong"
            )
        }
        return ValidationResult(
            successful = true
        )
    }


}
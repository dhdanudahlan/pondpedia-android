package com.pondpedia.android.pondpedia.domain.use_case.auth.signup

import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.data.ValidationResult

class ValidateInformationSourceUseCase {

    operator fun invoke(source: String): ValidationResult {
        if (source.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Mohon diisi"
            )
        }
        return ValidationResult(
            successful = true
        )
    }


}
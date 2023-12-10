package com.pondpedia.android.pondpedia.domain.use_case.auth.signup

import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.data.ValidationResult

class ValidateTermsUseCase {

    operator fun invoke(acceptedTerms: Boolean): ValidationResult {
        if (!acceptedTerms) {
            return ValidationResult(
                successful = false,
                errorMessage = "Anda belum menyetujui ketentuan"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}
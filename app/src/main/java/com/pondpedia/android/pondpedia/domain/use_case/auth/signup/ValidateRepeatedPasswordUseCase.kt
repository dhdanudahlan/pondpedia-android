package com.pondpedia.android.pondpedia.domain.use_case.auth.signup

import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.data.ValidationResult

class ValidateRepeatedPasswordUseCase {

    operator fun invoke(password: String, repeatedPassword: String): ValidationResult {
        if (password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password dan tidak sama"
            )
        }
        return ValidationResult(
            successful = true
        )
    }


}
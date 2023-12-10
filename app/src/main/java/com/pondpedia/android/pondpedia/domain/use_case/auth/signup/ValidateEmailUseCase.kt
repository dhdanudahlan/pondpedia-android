package com.pondpedia.android.pondpedia.domain.use_case.auth.signup

import android.util.Log
import android.util.Patterns
import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.data.ValidationResult

class ValidateEmailUseCase {

    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Email tidak boleh kosong"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Log.d("ValidateEmailUseCase INVALID", "'$email'")
            return ValidationResult(
                successful = false,
                errorMessage = "Email tidak valid"
            )
        }
        Log.d("ValidateEmailUseCase VALID", "'$email'")
        return ValidationResult(
            successful = true
        )
    }


}
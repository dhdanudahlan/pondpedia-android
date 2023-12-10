package com.pondpedia.android.pondpedia.domain.use_case.auth.signup

import android.util.Log
import android.util.Patterns
import com.pondpedia.android.pondpedia.presentation.ui.auth.sign_up.data.ValidationResult

class ValidatePhoneNumberUseCase {

    operator fun invoke(phoneNumber: String): ValidationResult {
        if (phoneNumber.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Masukkan nomor HP anda"
            )
        }
        val containsLetters = phoneNumber.any { it.isLetter() }
        if (containsLetters) {
            return ValidationResult(
                successful = false,
                errorMessage = "Nomor HP tidak valid"
            )
        }
        return ValidationResult(
            successful = true
        )
    }


}
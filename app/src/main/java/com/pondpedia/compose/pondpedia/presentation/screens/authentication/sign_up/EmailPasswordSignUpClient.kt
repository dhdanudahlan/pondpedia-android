package com.pondpedia.compose.pondpedia.presentation.screens.authentication.sign_up


/*
class EmailPasswordSignUpClient {
    private val auth = Firebase.auth

    suspend fun signUpWithEmailAndPassword(email: String, password: String): SignUpResult {
        return try {
            val user = auth.createUserWithEmailAndPassword(email, password).await().user
            SignUpResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        pictureUrl = photoUrl?.toString(),
                        userEmail = email
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignUpResult(
                data = null,
                errorMessage = e.message
            )
        }
    }
}*/


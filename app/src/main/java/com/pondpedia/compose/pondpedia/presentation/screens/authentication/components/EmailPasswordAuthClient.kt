package com.pondpedia.compose.pondpedia.presentation.screens.authentication.components

/*
class EmailPasswordAuthClient {
    private val auth = Firebase.auth

    suspend fun signInWithEmailAndPassword(email: String, password: String): SignInResult {
        return try {
            val user = auth.signInWithEmailAndPassword(email, password).await().user
            SignInResult(
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
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }
}*/

package com.pondpedia.compose.pondpedia.presentation.screens.authentication.components


/*

class AuthViewModel(
    private val emailPasswordAuthClient: EmailPasswordAuthClient,
    private val emailPasswordSignUpClient: EmailPasswordSignUpClient,
    private val dataStore: PondPediaDataStore
): ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun onEmailPasswordSignIn(email: String, password: String) {
        viewModelScope.launch {
            val signInResult = emailPasswordAuthClient.signInWithEmailAndPassword(email, password)
            _state.update {
                it.copy(
                    isSignInSuccessful = signInResult.data != null,
                    signInError = signInResult.errorMessage
                )
            }
        }
    }

    fun onEmailPasswordSignUp(email: String, password: String) {
        viewModelScope.launch {
            val signUpResult = emailPasswordSignUpClient.signUpWithEmailAndPassword(email, password)
            _state.update {
                it.copy(
                    isSignUpSuccessful = signUpResult.data != null,
                    signUpError = signUpResult.errorMessage
                )
            }
        }
    }

    fun resetState() {
        _state.update { AuthState() }
    }

    fun saveLogin(userData: UserData?) {
        */
/*viewModelScope.launch {
            if (userData != null) {
                dataStore.saveLogin(userData)
            } else {
                dataStore.saveLogin(UserData("Guest", "Guest", "Guest", "Guest@email.com"))
            }
        }*//*

    }
}*/

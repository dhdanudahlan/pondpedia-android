package com.pondpedia.android.pondpedia.presentation.ui.home.more.profile.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pondpedia.android.pondpedia.core.util.Resource
import com.pondpedia.android.pondpedia.domain.model.auth.Farmer
import com.pondpedia.android.pondpedia.domain.model.auth.Response.Loading
import com.pondpedia.android.pondpedia.domain.model.auth.Response.Success
import com.pondpedia.android.pondpedia.domain.repository.AuthRepository
import com.pondpedia.android.pondpedia.domain.repository.ProfileRepository
import com.pondpedia.android.pondpedia.domain.repository.ReloadUserResponse
import com.pondpedia.android.pondpedia.domain.repository.RevokeAccessResponse
import com.pondpedia.android.pondpedia.domain.repository.SendEmailVerificationResponse
import com.pondpedia.android.pondpedia.domain.repository.SignOutResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository
): ViewModel() {

    var signOutResponse by mutableStateOf<SignOutResponse>(Success(false))
        private set
    var revokeAccessResponse by mutableStateOf<RevokeAccessResponse>(Success(false))
        private set
    var reloadUserResponse by mutableStateOf<ReloadUserResponse>(Success(false))
        private set

    private val _currentUser = MutableStateFlow(Farmer())
    val currentUser = _currentUser.asStateFlow()

    fun reloadUser() = viewModelScope.launch {
        reloadUserResponse = Loading
        reloadUserResponse = authRepository.reloadFirebaseUser()
    }

    val isEmailVerified get() = authRepository.currentUser?.isEmailVerified ?: false

    var sendEmailVerificationResponse by mutableStateOf<SendEmailVerificationResponse>(Success(false))
        private set

    fun sendEmailVerification() = viewModelScope.launch {
        sendEmailVerificationResponse = Loading
        sendEmailVerificationResponse = authRepository.sendEmailVerification()
    }

    fun getCurrentUser() {
        viewModelScope.launch {
            val user = async { profileRepository.getUser() }
            _currentUser.value = user.await().data ?: Farmer()
        }
    }

    fun signOut(
        onSuccess: () -> Unit = {},
        onError: (String) -> Unit = {}
    ) {
        viewModelScope.launch {
            when (val result = authRepository.signOut()) {
                is Resource.Success -> onSuccess.invoke()
                is Resource.Error -> onError.invoke(result.message ?: "Gagal keluar")
                else -> {}
            }
        }
    }

    fun revokeAccess() = viewModelScope.launch {
        revokeAccessResponse = Loading
        revokeAccessResponse = authRepository.revokeAccess()
    }
}
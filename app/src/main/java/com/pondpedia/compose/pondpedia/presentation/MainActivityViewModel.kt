package com.pondpedia.compose.pondpedia.presentation

import com.pondpedia.compose.pondpedia.domain.model.auth.Farmer

/*@HiltViewModel
class MainActivityViewModel @Inject constructor(
    farmerRepository: FarmerRepository,
) : ViewModel() {
    val uiState: StateFlow<MainActivityUiState> = farmerRepository.farmer.map {
        Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )
}*/

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val farmer: Farmer) : MainActivityUiState
}
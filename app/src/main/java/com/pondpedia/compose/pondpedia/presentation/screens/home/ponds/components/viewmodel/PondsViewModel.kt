package com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pondpedia.compose.pondpedia.core.util.ponds.PondsCategoryFilterType
import com.pondpedia.compose.pondpedia.core.util.ponds.PondsHarvestFilterType
import com.pondpedia.compose.pondpedia.core.util.ponds.PondsPriorityFilterType
import com.pondpedia.compose.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.compose.pondpedia.domain.model.ponds.PondDummyGenerator
import com.pondpedia.compose.pondpedia.domain.model.ponds.PondLog
import com.pondpedia.compose.pondpedia.domain.use_case.DeletePondLogUseCase
import com.pondpedia.compose.pondpedia.domain.use_case.GetPondCategoriesUseCase
import com.pondpedia.compose.pondpedia.domain.use_case.GetPondLogByIdUseCase
import com.pondpedia.compose.pondpedia.domain.use_case.GetPondLogListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PondsViewModel @Inject constructor(
    private val getPondCategoriesUseCase: GetPondCategoriesUseCase,
    private val getPondLogListUseCase: GetPondLogListUseCase,
    private val getPondLogByIdUseCase: GetPondLogByIdUseCase,
    private val deletePondLogUseCase: DeletePondLogUseCase
) : ViewModel() {

    private val _pondId = mutableStateOf("")
    val pondId: State<String> = _pondId

    private val _state = mutableStateOf(PondsState())
    val state: State<PondsState> = _state

    init {
        getPondCategories()
        getPondList()
    }

    fun setSelectedTab(index: Int) {
        Log.d("PondsViewModel", "Selected Tab Index changed into -> $index")
        _state.value = _state.value.copy(
            selectedTabIndex = index
        )
    }

    fun setSelectedPondId(pondId: String){
        _state.value = _state.value.copy(
            pondId = pondId,
        )
        getPondList()
    }
    fun setFilterCategorized(filter: PondsCategoryFilterType){
        _state.value = _state.value.copy(
            filterCategorized = filter
        )
        getPondList()
    }
    fun setFilterPrioritized(filter: PondsPriorityFilterType){
        _state.value = _state.value.copy(
            filterPrioritized = filter
        )
        getPondList()
    }
    fun setFilterHarvested(filter: PondsHarvestFilterType){
        _state.value = _state.value.copy(
            filterHarvested = filter
        )
        getPondList()
    }
    private fun getPondCategories() {
        val categoriesLiveData = getPondCategoriesUseCase.invoke()

        categoriesLiveData.observeForever { newCategories ->
            val categories = mutableListOf("Semua")
            newCategories.forEach { category ->
                if (category.isNotEmpty()) {
                    categories.add(category)
                }
            }
            _state.value = _state.value.copy(
                pondTabList = categories
            )
        }
    }
    fun getPondLogById(pondId: String) {
        _state.value = _state.value.copy(
            pondLog = getPondLogByIdUseCase(pondId)
        )
    }

    fun getPondList(
    ) {
        val pondLogLiveData = getPondLogListUseCase.invoke(
            categoryFilterType = _state.value.filterCategorized,
            priorityFilterType = _state.value.filterPrioritized,
            harvestFilterType = _state.value.filterHarvested,
            category = if (_state.value.selectedTabIndex != 0) _state.value.pondTabList[_state.value.selectedTabIndex] else "",
        )
        pondLogLiveData.observeForever { pondLogList ->
            _state.value = _state.value.copy(
                pondLogList = pondLogList
            )
        }
    }


    fun deletePond(pondId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deletePondLogUseCase(pondId)
        }
    }
}

data class PondsState(
    val isLoading: Boolean = false,
    val pondTabList: List<String> = listOf(""),
    val pondLogList: List<PondLog> = emptyList(),
    val pondId: String = "",
    val pondLog: PondLog = PondDummyGenerator.getDummyPondLog(),
    val selectedTabIndex: Int = 0,

    val pondData: Pond = PondDummyGenerator.getDummyPond(),

    val filterCategorized: PondsCategoryFilterType = PondsCategoryFilterType.ALL,
    val filterPrioritized: PondsPriorityFilterType = PondsPriorityFilterType.ALL,
    val filterHarvested: PondsHarvestFilterType = PondsHarvestFilterType.ALL,
)
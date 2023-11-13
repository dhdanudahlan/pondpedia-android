package com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pondpedia.compose.pondpedia.core.util.DateGenerator
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.CategoryEntity
import com.pondpedia.compose.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.compose.pondpedia.domain.use_case.ponds.AddPondUseCase
import com.pondpedia.compose.pondpedia.domain.use_case.ponds.DeletePondByIdUseCase
import com.pondpedia.compose.pondpedia.domain.use_case.ponds.GetCategoryListUseCase
import com.pondpedia.compose.pondpedia.domain.use_case.ponds.GetPondByIdUseCase
import com.pondpedia.compose.pondpedia.domain.use_case.ponds.GetPondListByCategoryUseCase
import com.pondpedia.compose.pondpedia.domain.use_case.ponds.UpsertCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class PondsViewModel @Inject constructor(
    private val addPondUseCase: AddPondUseCase,
    private val upsertCategoryUseCase: UpsertCategoryUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val getPondListUseCase: GetPondListByCategoryUseCase,
    private val getPondByIdUseCase: GetPondByIdUseCase,
    private val deletePondByIdUseCase: DeletePondByIdUseCase
) : ViewModel() {
    private val _sortType = MutableStateFlow(SortType.CREATED_DATE)

    private val _selectedCategoryIndex = MutableStateFlow(0)

    private val _selectedPondId = MutableStateFlow((0).toLong())

    private val _categories = getCategoryListUseCase().map { it }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _ponds = combine(_sortType, _selectedCategoryIndex) { sortType, selectedCategoryIndex ->
        getPondListUseCase(sortType, "-")
    }.flatMapLatest { it }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _pond= _selectedPondId.flatMapLatest { selectedPondId ->
        getPondByIdUseCase(selectedPondId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Pond())



    private val _state = MutableStateFlow(PondsState())
    private val _stateTwo = combine(_state, _sortType, _selectedCategoryIndex, _categories, _ponds) { state, sortType, selectedCategoryIndex, categories, ponds ->
        state.copy(
            sortType = sortType,
            selectedCategoryIndex = selectedCategoryIndex,
            categories = categories,
            ponds = ponds
        )
    }.stateIn(this.viewModelScope, SharingStarted.WhileSubscribed(5000), PondsState())
    val state = combine(_stateTwo, _pond) { state, pond ->
        state.copy(
            pond = pond
        )
    }.stateIn(this.viewModelScope, SharingStarted.WhileSubscribed(5000), PondsState())



    fun onEvent(event: PondsEvent) {
        when(event){
            PondsEvent.AddPond -> {
                val name = state.value.name
                val area = state.value.area
                val depth = state.value.depth
                val pondType = state.value.pondType
                val waterType = state.value.waterType
                val location = state.value.location
                val description = state.value.description
                val createdDate = DateGenerator.getCurrentDate()
                val updatedDate = DateGenerator.getCurrentDate()
                val farmerId = state.value.farmerId
                if (name.isBlank() || area.isBlank() || depth.isBlank() || pondType.isBlank() || waterType.isBlank()) {
                    return
                }

                val pond = Pond(
                    name = name,
                    area = area,
                    depth = depth,
                    pondType = pondType,
                    waterType = waterType,
                    location = location,
                    description = description,
                    createdDate = createdDate,
                    updatedDate = updatedDate,
                    farmerId = farmerId
                )

                viewModelScope.launch(Dispatchers.IO) {
                    addPondUseCase(pond = pond )
                }

                _state.update {
                    it.copy(
                        isAddingPond = false,
                        name = "",
                        area = "",
                        depth = "",
                        pondType = "",
                        waterType = "",
                        description = "",
                        createdDate = DateGenerator.getCurrentDateTime(),
                        updatedDate = DateGenerator.getCurrentDateTime(),
                        farmerId = 0,
                    )
                }
            }
            is PondsEvent.DeletePond -> {
                val pondId = event.selectedPondId

                viewModelScope.launch(Dispatchers.IO) {
                    deletePondByIdUseCase(pondId)
                }
            }
            PondsEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingPond = false
                ) }
            }
            PondsEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingPond = true
                ) }
            }
            is PondsEvent.SelectPond -> {
                _selectedPondId.value = event.selectedPondId
            }

            is PondsEvent.SelectCategory ->  {
                _selectedCategoryIndex.value = event.selectedCategory
            }

            is PondsEvent.SortPonds ->  {
                _sortType.value = event.sortType
            }

            is PondsEvent.SetName -> {
                _state.update { it.copy(
                    name = event.name
                ) }
            }

            is PondsEvent.SetArea -> {
                _state.update { it.copy(
                    area = event.area
                ) }
            }

            is PondsEvent.SetDepth -> {
                _state.update { it.copy(
                    depth = event.depth
                ) }
            }

            is PondsEvent.SetLocation -> {
                _state.update { it.copy(
                    location = event.location
                ) }
            }

            is PondsEvent.SetPondType -> {
                _state.update { it.copy(
                    pondType = event.pondType
                ) }
            }

            is PondsEvent.SetWaterType -> {
                _state.update { it.copy(
                    waterType = event.waterType
                ) }
            }

            is PondsEvent.SetDescription -> {
                _state.update { it.copy(
                    description = event.description
                ) }
            }

            is PondsEvent.SetFarmerId -> {
                _state.update { it.copy(
                    farmerId = event.farmerId
                ) }
            }

        }
    }
    suspend fun upsertCategory(categoryEntity: CategoryEntity){
        withContext(Dispatchers.IO) {
            upsertCategoryUseCase()
        }
    }

    /*init {
        getPondCategories()
        getPondListByCategory()
    }*/

//    fun setSelectedPondId(pondId: Long){
//        _state.value = _state.value.copy(
//            selectedPondId = pondId,
//        )
//        getPondListByCategory()
//    }
//    fun setFilterCategorized(filter: PondsCategoryFilterType){
//        _state.value = _state.value.copy(
//            filterCategorized = filter
//        )
//        getPondListByCategory()
//    }
//    fun setFilterPrioritized(filter: PondsPriorityFilterType){
//        _state.value = _state.value.copy(
//            filterPrioritized = filter
//        )
//        getPondListByCategory()
//    }
//    fun setFilterHarvested(filter: PondsHarvestFilterType){
//        _state.value = _state.value.copy(
//            filterHarvested = filter
//        )
//        getPondListByCategory()
//    }
//    private fun getPondCategories() {
//        val categoryList = getCategoryListUseCase.invoke()
//
//        _state.value = _state.value.copy(
//            pondCategoryList = categoryList
//        )
//    }
//    fun getPondById(pondId: Long) {
//        _state.value = _state.value.copy(
//            selectedPondId = pondId
//        )
//    }
//
//    fun getPondListByCategory(
//    ) {
//        val pondLiveData = getPondListUseCase.invoke(
//            categoryName = state.value.selectedCategoryIndex,
//        )
//        pondLiveData.observeForever { pondList ->
//            _state.value = _state.value.copy(
//                pondList = pondList
//            )
//        }
//    }
//
//
//    fun deletePondById(pondId: Long) {
//        viewModelScope.launch(Dispatchers.IO) {
//            deletePondByIdUseCase(pondId)
//        }
//    }
}



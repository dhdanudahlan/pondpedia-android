package com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.CommodityWithGrowthRecords
import com.pondpedia.compose.pondpedia.data.local.entity.pond_management.relations.CommodityWithHealthRecords
import com.pondpedia.compose.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.compose.pondpedia.domain.use_case.ponds.DeletePondByIdUseCase
import com.pondpedia.compose.pondpedia.domain.use_case.ponds.GetPondByIdUseCase
import com.pondpedia.compose.pondpedia.domain.use_case.ponds.UpsertCategoryUseCase
import com.pondpedia.compose.pondpedia.domain.use_case.ponds.pond_details.GetCommodityListByPondIdUseCase
import com.pondpedia.compose.pondpedia.domain.use_case.ponds.pond_details.GetCommodityWithGrowthRecordsByCommodityIdUseCase
import com.pondpedia.compose.pondpedia.domain.use_case.ponds.pond_details.GetCommodityWithHealthRecordsByCommodityIdUseCase
import com.pondpedia.compose.pondpedia.domain.use_case.ponds.pond_details.GetFeedingRecordsByPondIdUseCase
import com.pondpedia.compose.pondpedia.domain.use_case.ponds.pond_details.GetPondRecordsByPondIdUseCase
import com.pondpedia.compose.pondpedia.domain.use_case.ponds.pond_details.GetWaterRecordsByPondIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PondDetailsViewModel @Inject constructor(
    private val upsertCategoryUseCase: UpsertCategoryUseCase,
    private val getPondByIdUseCase: GetPondByIdUseCase,
    private val getPondRecordsByPondIdUseCase: GetPondRecordsByPondIdUseCase,
    private val getWaterRecordsByPondIdUseCase: GetWaterRecordsByPondIdUseCase,
    private val getFeedingRecordsByPondIdUseCase: GetFeedingRecordsByPondIdUseCase,
    private val getCommodityListByPondIdUseCase: GetCommodityListByPondIdUseCase,
    private val getCommodityWithGrowthRecordsByCommodityIdUseCase: GetCommodityWithGrowthRecordsByCommodityIdUseCase,
    private val getCommodityWithHealthRecordsByCommodityIdUseCase: GetCommodityWithHealthRecordsByCommodityIdUseCase,
    private val deletePondByIdUseCase: DeletePondByIdUseCase
) : ViewModel() {
    private val _selectedPondId = MutableStateFlow((0).toLong())
    private val _selectedCommodityId = MutableStateFlow((0).toLong())

    private val _pond= _selectedPondId.flatMapLatest { selectedPondId ->
        getPondByIdUseCase(selectedPondId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Pond())

    private val _pondRecords= _selectedPondId.flatMapLatest { selectedPondId ->
        getPondRecordsByPondIdUseCase(selectedPondId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _waterRecords= _selectedPondId.flatMapLatest { selectedPondId ->
        getWaterRecordsByPondIdUseCase(selectedPondId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _feedingRecords= _selectedPondId.flatMapLatest { selectedPondId ->
        getFeedingRecordsByPondIdUseCase(selectedPondId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _commodity = _selectedPondId.flatMapLatest { selectedPondId ->
        getCommodityListByPondIdUseCase(selectedPondId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _commodityWithGrowthRecords = _selectedCommodityId.flatMapLatest { selectedCommodityId ->
        getCommodityWithGrowthRecordsByCommodityIdUseCase(selectedCommodityId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CommodityWithGrowthRecords())

    private val _commodityWithHealthRecords = _selectedCommodityId.flatMapLatest { selectedCommodityId ->
        getCommodityWithHealthRecordsByCommodityIdUseCase(selectedCommodityId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CommodityWithHealthRecords())




    private val _state = MutableStateFlow(PondDetailsState())
    private val _stateTwo = combine(_state, _pond, _selectedPondId, _commodity, _selectedCommodityId) { state, pond, selectedPondId, commodity, selectedCommodityId ->
        state.copy(
            pondId = selectedPondId,
            pond = pond,
            commodityId = selectedCommodityId,
            commodity = commodity
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PondDetailsState())
    val state = combine(_stateTwo, _pondRecords) { state, pondRecords ->
        state.copy(
            pondRecords = pondRecords
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PondDetailsState())

    fun onEvent(event: PondDetailsEvent){
        when(event) {
            PondDetailsEvent.AddCommodity -> TODO()
            PondDetailsEvent.AddCommodityGrowthRecords -> TODO()
            PondDetailsEvent.AddCommodityHeathRecords -> TODO()
            PondDetailsEvent.AddFeed -> TODO()
            PondDetailsEvent.AddFeedingRecords -> TODO()
            PondDetailsEvent.AddPondCategoryCrossRef -> TODO()
            PondDetailsEvent.AddPondRecords -> TODO()
            PondDetailsEvent.AddWaterRecords -> TODO()
            PondDetailsEvent.DeletePond -> TODO()
            PondDetailsEvent.EditPond -> TODO()
            is PondDetailsEvent.SetCommodityDate -> TODO()
            is PondDetailsEvent.SetCommodityEntity -> TODO()
            is PondDetailsEvent.SetCommodityGrowthRecordsAge -> TODO()
            is PondDetailsEvent.SetCommodityGrowthRecordsDate -> TODO()
            is PondDetailsEvent.SetCommodityGrowthRecordsLength -> TODO()
            is PondDetailsEvent.SetCommodityGrowthRecordsNote -> TODO()
            is PondDetailsEvent.SetCommodityGrowthRecordsWeight -> TODO()
            is PondDetailsEvent.SetCommodityHealthRecordsAction -> TODO()
            is PondDetailsEvent.SetCommodityHealthRecordsDate -> TODO()
            is PondDetailsEvent.SetCommodityHealthRecordsDeath -> TODO()
            is PondDetailsEvent.SetCommodityHealthRecordsIndicator -> TODO()
            is PondDetailsEvent.SetCommodityHealthRecordsNote -> TODO()
            is PondDetailsEvent.SetCommodityName -> TODO()
            is PondDetailsEvent.SetCommodityOrigin -> TODO()
            is PondDetailsEvent.SetCommoditySciName -> TODO()
            is PondDetailsEvent.SetFeedDate -> TODO()
            is PondDetailsEvent.SetFeedName -> TODO()
            is PondDetailsEvent.SetFeedOrigin -> TODO()
            is PondDetailsEvent.SetFeedingRecordsDate -> TODO()
            is PondDetailsEvent.SetFeedingRecordsNote -> TODO()
            is PondDetailsEvent.SetFeedingRecordsQuantity -> TODO()
            is PondDetailsEvent.SetPondCategoryCrossRefCategoryName -> TODO()
            is PondDetailsEvent.SetPondId -> {
                _selectedPondId.value = event.pondId
            }
            is PondDetailsEvent.SetPondRecordsCycle -> TODO()
            is PondDetailsEvent.SetPondRecordsDate -> TODO()
            is PondDetailsEvent.SetPondRecordsNote -> TODO()
            is PondDetailsEvent.SetWaterRecordsColor -> TODO()
            is PondDetailsEvent.SetWaterRecordsDate -> TODO()
            is PondDetailsEvent.SetWaterRecordsLevel -> TODO()
            is PondDetailsEvent.SetWaterRecordsNote -> TODO()
            is PondDetailsEvent.SetWaterRecordsQuality -> TODO()
            is PondDetailsEvent.SetCommodityId -> TODO()
        }
    }

}

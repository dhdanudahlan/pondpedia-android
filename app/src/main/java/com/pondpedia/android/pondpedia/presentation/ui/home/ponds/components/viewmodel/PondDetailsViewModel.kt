@file:OptIn(ExperimentalCoroutinesApi::class)

package com.pondpedia.android.pondpedia.presentation.ui.home.ponds.components.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pondpedia.android.pondpedia.core.util.StringParser
import com.pondpedia.android.pondpedia.domain.model.pond_management.Commodity
import com.pondpedia.android.pondpedia.domain.model.pond_management.CommodityGrowthRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.CommodityHealthRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.new_model.FeedingRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.android.pondpedia.domain.model.pond_management.PondRecords
import com.pondpedia.android.pondpedia.domain.model.pond_management.WaterRecords
import com.pondpedia.android.pondpedia.domain.use_case.ponds.DeletePondByIdUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.GetPondByIdUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.UpsertCategoryUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.AddCommodityGrowthRecordsUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.AddCommodityHealthRecordsUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.AddCommodityUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.AddFeedingRecordsUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.AddPondRecordsUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.AddWaterRecordsUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.GetCommodityGrowthRecordsByCommodityIdUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.GetCommodityHealthRecordsByCommodityIdUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.GetCommodityListByPondIdUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.GetFeedingRecordsByPondIdUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.GetPondRecordsByPondIdUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.GetWaterRecordsByPondIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PondDetailsViewModel @Inject constructor(
    private val upsertCategoryUseCase: UpsertCategoryUseCase,
    private val addCommodityUseCase: AddCommodityUseCase,
    private val addCommodityGrowthRecordsUseCase: AddCommodityGrowthRecordsUseCase,
    private val addCommodityHealthRecordsUseCase: AddCommodityHealthRecordsUseCase,
    private val addFeedingRecordsUseCase: AddFeedingRecordsUseCase,
    private val addPondRecordsUseCase: AddPondRecordsUseCase,
    private val addWaterRecordsUseCase: AddWaterRecordsUseCase,
    private val getPondByIdUseCase: GetPondByIdUseCase,
    private val getPondRecordsByPondIdUseCase: GetPondRecordsByPondIdUseCase,
    private val getWaterRecordsByPondIdUseCase: GetWaterRecordsByPondIdUseCase,
    private val getFeedingRecordsByPondIdUseCase: GetFeedingRecordsByPondIdUseCase,
    private val getCommodityListByPondIdUseCase: GetCommodityListByPondIdUseCase,
    private val getCommodityGrowthRecordsByCommodityIdUseCase: GetCommodityGrowthRecordsByCommodityIdUseCase,
    private val getCommodityHealthRecordsByCommodityIdUseCase: GetCommodityHealthRecordsByCommodityIdUseCase,
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

    private val _commodityGrowthRecords = _selectedCommodityId.flatMapLatest { selectedCommodityId ->
        getCommodityGrowthRecordsByCommodityIdUseCase(selectedCommodityId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), listOf())

    private val _commodityHealthRecords = _selectedCommodityId.flatMapLatest { selectedCommodityId ->
        getCommodityHealthRecordsByCommodityIdUseCase(selectedCommodityId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), listOf())




    private val _state = MutableStateFlow(PondDetailsState())
    private val _stateTwo = combine(_state, _pond, _selectedPondId, _commodity, _selectedCommodityId) { state, pond, selectedPondId, commodity, selectedCommodityId ->
        state.copy(
            pondId = selectedPondId,
            pond = pond,
            commodityId = selectedCommodityId,
            commodity = commodity
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PondDetailsState())

    private val _stateThree = combine(_stateTwo, _waterRecords, _feedingRecords, _commodityGrowthRecords, _commodityHealthRecords) { state, waterRecords, feedingRecords, commodityGrowthRecords, commodityHealthRecords ->
        state.copy(
            waterRecords = waterRecords,
            feedingRecords = feedingRecords,
            commodityGrowthRecords = commodityGrowthRecords,
            commodityHealthRecords = commodityHealthRecords
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PondDetailsState())

    val state = combine(_stateThree, _pondRecords) { state, pondRecords ->
        state.copy(
            pondRecords = pondRecords
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PondDetailsState())

    fun onEvent(event: PondDetailsEvent) {
        when (event) {
            PondDetailsEvent.AddCommodity -> {

                val date = state.value.commodityDate

                val origin = state.value.commodityOrigin

                val quantity = state.value.commodityQuantity

                val name: String = state.value.commodityName

                val scientificName = state.value.commoditySciName

                val pondId = state.value.pond.pondId

                val commodity = Commodity(0, date, origin, quantity, name, scientificName, pondId)

                if (date.isBlank() || quantity.isBlank() || name.isBlank()) {
                    return
                }

                viewModelScope.launch(Dispatchers.IO) {
                    addCommodityUseCase(commodity)
                }

                _state.update {
                    it.copy(
                        isAddingCommodity = false,
                        commodityDate = "",
                        commodityOrigin = "",
                        commodityQuantity = "",
                        commodityName = "",
                        commoditySciName = "",
                    )
                }
            }

            PondDetailsEvent.AddCommodityGrowthRecords -> {

                val date = state.value.commodityGrowthRecordsDate

                val age = state.value.commodityGrowthRecordsAge

                val length = state.value.commodityGrowthRecordsLength

                val weight = state.value.commodityGrowthRecordsWeight

                val note = state.value.commodityGrowthRecordsNote

                val commodityId = state.value.commodityId

                val commodityGrowthRecords = CommodityGrowthRecords(
                    recordId = 0,
                    date = date,
                    age = StringParser.toIntAbs(age) ?: 0,
                    length = StringParser.toIntAbs(length) ?: 0,
                    weight = StringParser.toIntAbs(weight) ?: 0,
                    note = note,
                    commodityId = commodityId
                )

                if (date.isBlank() || age.isBlank() || length.isBlank() || weight.isBlank()) {
                    return
                }
                viewModelScope.launch(Dispatchers.IO) {
                    addCommodityGrowthRecordsUseCase(commodityGrowthRecords)
                }

                _state.update {
                    it.copy(
                        commodityGrowthRecordsDate = "",
                        commodityGrowthRecordsAge = "",
                        commodityGrowthRecordsLength = "",
                        commodityGrowthRecordsWeight = "",
                        commodityGrowthRecordsNote = "",
                    )
                }
            }

            PondDetailsEvent.AddCommodityHeathRecords -> {

                val date = state.value.commodityHealthRecordsDate

                val death = state.value.commodityHealthRecordsDeath

                val indicator = state.value.commodityHealthRecordsIndicator

                val action = state.value.commodityHealthRecordsAction

                val note = state.value.commodityHealthRecordsNote

                val commodityId = state.value.commodityId

                val commodityHealthRecords = CommodityHealthRecords(
                    recordId = 0,
                    date = date,
                    death = StringParser.toIntAbs(death) ?: 0,
                    indicator = indicator,
                    action = action,
                    note = note,
                    commodityId = commodityId
                )

                if (date.isBlank() || death.isBlank() || indicator.isBlank() || action.isBlank()) {
                    return
                }
                viewModelScope.launch(Dispatchers.IO) {
                    addCommodityHealthRecordsUseCase(commodityHealthRecords)
                }

                _state.update {
                    it.copy(
                        commodityHealthRecordsDate = "",
                        commodityHealthRecordsDeath = "",
                        commodityHealthRecordsIndicator = "",
                        commodityHealthRecordsAction = "",
                        commodityHealthRecordsNote = "",
                    )
                }
            }

            PondDetailsEvent.AddFeed -> {

            }

            PondDetailsEvent.AddFeedingRecords -> {

                val date = state.value.feedingRecordsDate

                val quantity = state.value.feedingRecordsQuantity

                val note = state.value.feedingRecordsNote

                val feedId = state.value.feedId

                val pondId = state.value.pond.pondId

                val feedingRecordsUseCase = FeedingRecords(
                    recordId = 0,
                    date = date,
                    quantity = StringParser.toFloatAbs(quantity) ?: 0.toFloat(),
                    note = note,
                    feedId = feedId,
                    pondId = pondId
                )

                if (date.isBlank() || quantity.isBlank()) {
                    return
                }

                viewModelScope.launch(Dispatchers.IO) {
                    addFeedingRecordsUseCase(feedingRecordsUseCase)
                }

                _state.update {
                    it.copy(
                        feedingRecordsDate = "",
                        feedingRecordsQuantity = "",
                        feedingRecordsNote = "",
                    )
                }
            }

            PondDetailsEvent.AddPondCategoryCrossRef -> {

            }

            PondDetailsEvent.AddPondRecords -> {

                val date = state.value.pondRecordsDate

                val cycle = state.value.pondRecordsCycle

                val note = state.value.pondRecordsNote

                val pondId = state.value.pond.pondId

                val pondRecords = PondRecords(0, date, cycle, note, pondId)

                if (date.isBlank() || cycle.isBlank()) {
                    return
                }

                viewModelScope.launch(Dispatchers.IO) {
                    addPondRecordsUseCase(pondRecords)
                }

                _state.update {
                    it.copy(
                        pondRecordsDate = "",
                        pondRecordsCycle = "",
                        pondRecordsNote = "",
                    )
                }
            }

            PondDetailsEvent.AddWaterRecords -> {

                val date = state.value.waterRecordsDate

                val level = state.value.waterRecordsLevel

                val color = state.value.waterRecordsColor

                val pH = state.value.waterRecordsPH

                val temperature = state.value.waterRecordsTemperature

                val weather = state.value.waterRecordsWeather

                val dissolvedOxygen = state.value.waterRecordsDissolvedOxygen

                val salinity = state.value.waterRecordsSalinity

                val turbidity = state.value.waterRecordsTurbidity

                val clarity = state.value.waterRecordsClarity

                val note = state.value.waterRecordsNote

                val pondId = state.value.pond.pondId

                val waterRecords = WaterRecords(
                    0,
                    date = date,
                    level = StringParser.toInt(level)!!,
                    pH = StringParser.toFloat(pH),
                    temperature = StringParser.toFloat(temperature),
                    weather = if (weather.isNotBlank()) weather else "Cerah",
                    dissolvedOxygen = StringParser.toFloat(dissolvedOxygen),
                    salinity = StringParser.toFloat(salinity),
                    turbidity = StringParser.toFloat(turbidity),
                    clarity = StringParser.toFloat(clarity),
                    color = color,
                    note = note,
                    pondId = pondId
                )

                if (date.isBlank() || level.isBlank() || color.isBlank()) {
                    return
                }

                viewModelScope.launch(Dispatchers.IO) {
                    addWaterRecordsUseCase(waterRecords)
                }

                _state.update {
                    it.copy(
                        waterRecordsDate = "",
                        waterRecordsLevel = "",
                        waterRecordsPH = "",
                        waterRecordsTemperature = "",
                        waterRecordsWeather = "",
                        waterRecordsDissolvedOxygen = "",
                        waterRecordsSalinity = "",
                        waterRecordsTurbidity = "",
                        waterRecordsClarity = "",
                        waterRecordsColor = "",
                        waterRecordsNote = "",
                    )
                }
            }

            PondDetailsEvent.DeletePond -> {
                val pondId = state.value.pond.pondId
                viewModelScope.launch(Dispatchers.IO) {
                    deletePondByIdUseCase(pondId)
                }
            }

            PondDetailsEvent.EditPond -> {
            }

            is PondDetailsEvent.SetCommodityDate -> {
                _state.update {
                    it.copy(
                        commodityDate = event.commodityDate
                    )
                }
            }

            is PondDetailsEvent.SetCommodityEntity -> {
                _state.update {
                    it.copy(
                        commodityEntity = event.commodityEntity
                    )
                }
            }

            is PondDetailsEvent.SetCommodityGrowthRecordsAge -> {
                _state.update {
                    it.copy(
                        commodityGrowthRecordsAge = event.commodityGrowthRecordsAge
                    )
                }
            }

            is PondDetailsEvent.SetCommodityGrowthRecordsDate -> {
                _state.update {
                    it.copy(
                        commodityGrowthRecordsDate = event.commodityGrowthRecordsDate
                    )
                }
            }

            is PondDetailsEvent.SetCommodityGrowthRecordsLength -> {
                _state.update {
                    it.copy(
                        commodityGrowthRecordsLength = event.commodityGrowthRecordsLength
                    )
                }
            }

            is PondDetailsEvent.SetCommodityGrowthRecordsNote -> {
                _state.update {
                    it.copy(
                        commodityGrowthRecordsNote = event.commodityGrowthRecordsNote
                    )
                }
            }

            is PondDetailsEvent.SetCommodityGrowthRecordsWeight -> {
                _state.update {
                    it.copy(
                        commodityGrowthRecordsWeight = event.commodityGrowthRecordsWeight
                    )
                }
            }

            is PondDetailsEvent.SetCommodityHealthRecordsAction -> {
                _state.update {
                    it.copy(
                        commodityHealthRecordsAction = event.commodityHealthRecordsAction
                    )
                }
            }

            is PondDetailsEvent.SetCommodityHealthRecordsDate -> {
                _state.update {
                    it.copy(
                        commodityHealthRecordsDate = event.commodityHealthRecordsDate
                    )
                }
            }

            is PondDetailsEvent.SetCommodityHealthRecordsDeath -> {
                _state.update {
                    it.copy(
                        commodityHealthRecordsDeath = event.commodityHealthRecordsDeath
                    )
                }
            }

            is PondDetailsEvent.SetCommodityHealthRecordsIndicator -> {
                _state.update {
                    it.copy(
                        commodityHealthRecordsIndicator = event.commodityHealthRecordsIndicator
                    )
                }
            }

            is PondDetailsEvent.SetCommodityHealthRecordsNote -> {
                _state.update {
                    it.copy(
                        commodityHealthRecordsNote = event.commodityHealthRecordsNote
                    )
                }
            }

            is PondDetailsEvent.SetCommodityName -> {
                _state.update {
                    it.copy(
                        commodityName = event.commodityName
                    )
                }
            }

            is PondDetailsEvent.SetCommodityQuantity -> {
                _state.update {
                    it.copy(
                        commodityQuantity = event.commodityQuantity
                    )
                }
            }

            is PondDetailsEvent.SetCommodityOrigin -> {
                _state.update {
                    it.copy(
                        commodityOrigin = event.commodityOrigin
                    )
                }
            }

            is PondDetailsEvent.SetCommoditySciName -> {
                _state.update {
                    it.copy(
                        commoditySciName = event.commoditySciName
                    )
                }
            }

            is PondDetailsEvent.SetFeedDate -> {
                _state.update {
                    it.copy(
                        feedDate = event.feedDate
                    )
                }
            }

            is PondDetailsEvent.SetFeedName -> {
                _state.update {
                    it.copy(
                        feedName = event.feedName
                    )
                }
            }

            is PondDetailsEvent.SetFeedOrigin -> {
                _state.update {
                    it.copy(
                        feedOrigin = event.feedOrigin
                    )
                }
            }

            is PondDetailsEvent.SetFeedingRecordsDate -> {
                _state.update {
                    it.copy(
                        feedingRecordsDate = event.feedingRecordsDate
                    )
                }
            }

            is PondDetailsEvent.SetFeedingRecordsNote -> {
                _state.update {
                    it.copy(
                        feedingRecordsNote = event.feedingRecordsNote
                    )
                }
            }

            is PondDetailsEvent.SetFeedingRecordsQuantity -> {
                _state.update {
                    it.copy(
                        feedingRecordsQuantity = event.feedingRecordsQuantity
                    )
                }
            }

            is PondDetailsEvent.SetPondCategoryCrossRefCategoryName -> {
                _state.update {
                    it.copy(
                        pondCategoryCrossRefCategoryName = event.pondCategoryCrossRefCategoryName
                    )
                }
            }

            is PondDetailsEvent.SetPondId -> {
                _selectedPondId.value = event.pondId
            }

            is PondDetailsEvent.SetPondRecordsCycle -> {
                _state.update {
                    it.copy(
                        pondRecordsCycle = event.pondRecordsCycle
                    )
                }
            }

            is PondDetailsEvent.SetPondRecordsDate -> {
                _state.update {
                    it.copy(
                        pondRecordsDate = event.pondRecordsDate
                    )
                }
            }

            is PondDetailsEvent.SetPondRecordsNote -> {
                _state.update {
                    it.copy(
                        pondRecordsNote = event.pondRecordsNote
                    )
                }
            }

            is PondDetailsEvent.SetWaterRecordsColor -> {
                _state.update {
                    it.copy(
                        waterRecordsColor = event.value
                    )
                }
            }

            is PondDetailsEvent.SetWaterRecordsDate -> {
                _state.update {
                    it.copy(
                        waterRecordsDate = event.value
                    )
                }
            }

            is PondDetailsEvent.SetWaterRecordsLevel -> {
                _state.update {
                    it.copy(
                        waterRecordsLevel = event.value
                    )
                }
            }

            is PondDetailsEvent.SetWaterRecordsNote -> {
                _state.update {
                    it.copy(
                        waterRecordsNote = event.value
                    )
                }
            }

            is PondDetailsEvent.SetWaterRecordsPH -> {
                _state.update {
                    it.copy(
                        waterRecordsPH = event.value
                    )
                }
            }

            is PondDetailsEvent.SetWaterRecordsTemperature -> {
                _state.update {
                    it.copy(
                        waterRecordsTemperature = event.value
                    )
                }
            }

            is PondDetailsEvent.SetWaterRecordsWeather -> {
                _state.update {
                    it.copy(
                        waterRecordsWeather = event.value
                    )
                }
            }

            is PondDetailsEvent.SetWaterRecordsDissolvedOxygen -> {
                _state.update {
                    it.copy(
                        waterRecordsDissolvedOxygen = event.value
                    )
                }
            }

            is PondDetailsEvent.SetWaterRecordsSalinity -> {
                _state.update {
                    it.copy(
                        waterRecordsSalinity = event.value
                    )
                }
            }

            is PondDetailsEvent.SetWaterRecordsTurbidity -> {
                _state.update {
                    it.copy(
                        waterRecordsTurbidity = event.value
                    )
                }
            }

            is PondDetailsEvent.SetWaterRecordsClarity -> {
                _state.update {
                    it.copy(
                        waterRecordsClarity = event.value
                    )
                }
            }

            is PondDetailsEvent.SetCommodityId -> {
                _state.update {
                    it.copy(
                        commodityId = event.commodityId
                    )
                }
                _selectedCommodityId.value = event.commodityId
            }

            is PondDetailsEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        isAddingCommodity = true
                    )
                }
            }

            is PondDetailsEvent.HideDialog -> {
                _state.update {
                    it.copy(
                        isAddingCommodity = false
                    )
                }
            }
        }
    }
}

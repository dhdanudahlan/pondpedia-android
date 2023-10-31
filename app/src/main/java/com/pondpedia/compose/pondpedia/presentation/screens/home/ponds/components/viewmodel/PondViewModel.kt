package com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pondpedia.compose.pondpedia.domain.use_case.AddPondLogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class PondViewModel @Inject constructor(
    private val addPondLogUseCase: AddPondLogUseCase
) : ViewModel() {

    private val _pondId = mutableStateOf("")
    val pondId: State<String> = _pondId

    private val _state = mutableStateOf(PondState())
    val state: State<PondState> = _state


    fun setPondName(pondName: String) {
        _state.value = _state.value.copy(
            pondName = pondName
        )
    }

    fun setPondLength(pondLength: Float) {
        _state.value = _state.value.copy(
            pondLength = pondLength
        )
    }

    fun setPondWidth(pondWidth: Float) {
        _state.value = _state.value.copy(
            pondWidth = pondWidth
        )
    }

    fun setPondDepth(pondDepth: Float) {
        _state.value = _state.value.copy(
            pondDepth = pondDepth
        )
    }

    fun setPondImageUrl(pondImageUrl: String) {
        _state.value = _state.value.copy(
            pondImageUrl = pondImageUrl
        )
    }

    fun setFishId(fishId: Int) {
        _state.value = _state.value.copy(
            fishId = fishId
        )
    }

    fun setFishCommonName(fishCommonName: String) {
        _state.value = _state.value.copy(
            fishCommonName = fishCommonName
        )
    }

    fun setFishScientificName(fishScientificName: String) {
        _state.value = _state.value.copy(
            fishScientificName = fishScientificName
        )
    }

    fun setFishAmount(fishAmount: Int) {
        _state.value = _state.value.copy(
            fishAmount = fishAmount
        )
    }

    fun setFishTargetWeight(fishTargetWeight: Float) {
        _state.value = _state.value.copy(
            fishTargetWeight = fishTargetWeight
        )
    }

    fun setFishCurrentWeight(fishCurrentWeight: Float) {
        _state.value = _state.value.copy(
            fishCurrentWeight = fishCurrentWeight
        )
    }

    fun setFishCurrentLength(fishCurrentLength: Float) {
        _state.value = _state.value.copy(
            fishCurrentLength = fishCurrentLength
        )
    }

    fun setFeedId(feedId: Int) {
        _state.value = _state.value.copy(
            feedId = feedId
        )
    }

    fun setFeedName(feedName: String) {
        _state.value = _state.value.copy(
            feedName = feedName
        )
    }

    fun setFeedPercentage(feedPercentage: Float) {
        _state.value = _state.value.copy(
            feedPercentage = feedPercentage
        )
    }

    fun setFeedProtein(feedProtein: Float) {
        _state.value = _state.value.copy(
            feedProtein = feedProtein
        )
    }

    fun setFeedLipid(feedLipid: Float) {
        _state.value = _state.value.copy(
            feedLipid = feedLipid
        )
    }

    fun setFeedCarbohydrate(feedCarbohydrate: Float) {
        _state.value = _state.value.copy(
            feedCarbohydrate = feedCarbohydrate
        )
    }

    fun setFeedOthers(feedOthers: Float) {
        _state.value = _state.value.copy(
            feedOthers = feedOthers
        )
    }

    fun setFeedFrequency(feedFrequency: Int) {
        _state.value = _state.value.copy(
            feedFrequency = feedFrequency
        )
    }

    fun setWaterTemperature(waterTemperature: Float) {
        _state.value = _state.value.copy(
            waterTemperature = waterTemperature
        )
    }

    fun setWaterTurbidity(waterTurbidity: Float) {
        _state.value = _state.value.copy(
            waterTurbidity = waterTurbidity
        )
    }

    fun setWaterDissolvedOxygen(waterDissolvedOxygen: Float) {
        _state.value = _state.value.copy(
            waterDissolvedOxygen = waterDissolvedOxygen
        )
    }

    fun setWaterPH(waterPH: Float) {
        _state.value = _state.value.copy(
            waterPH = waterPH
        )
    }

    fun setWaterAmmonia(waterAmmonia: Float) {
        _state.value = _state.value.copy(
            waterAmmonia = waterAmmonia
        )
    }

    fun setWaterNitrate(waterNitrate: Float) {
        _state.value = _state.value.copy(
            waterNitrate = waterNitrate
        )
    }

    fun setPredWaterTemperature(predWaterTemperature: Float) {
        _state.value = _state.value.copy(
            predWaterTemperature = predWaterTemperature
        )
    }

    fun setPredWaterTurbidity(predWaterTurbidity: Float) {
        _state.value = _state.value.copy(
            predWaterTurbidity = predWaterTurbidity
        )
    }

    fun setPredWaterDissolvedOxygen(predWaterDissolvedOxygen: Float) {
        _state.value = _state.value.copy(
            predWaterDissolvedOxygen = predWaterDissolvedOxygen
        )
    }

    fun setPredWaterPH(predWaterPH: Float) {
        _state.value = _state.value.copy(
            predWaterPH = predWaterPH
        )
    }

    fun setPredWaterAmmonia(predWaterAmmonia: Float) {
        _state.value = _state.value.copy(
            predWaterAmmonia = predWaterAmmonia
        )
    }

    fun setPredWaterNitrate(predWaterNitrate: Float) {
        _state.value = _state.value.copy(
            predWaterNitrate = predWaterNitrate
        )
    }

    fun setPredGrowthLength(predGrowthLength: Float) {
        _state.value = _state.value.copy(
            predGrowthLength = predGrowthLength
        )
    }

    fun setPredGrowthFeed(predGrowthFeed: Float) {
        _state.value = _state.value.copy(
            predGrowthFeed = predGrowthFeed
        )
    }

    fun setPredGrowthDays(predGrowthDays: Float) {
        _state.value = _state.value.copy(
            predGrowthDays = predGrowthDays
        )
    }

    fun setCreatedDate() {
        _state.value = _state.value.copy(
            createdAt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now())
        )
    }

    fun setSelectedTab(index: Int) {
        Log.d("PondsViewModel", "Selected Tab Index changed into -> $index")
        _state.value = _state.value.copy(
            selectedTabIndex = index
        )
    }

    fun addPondToDatabase(userId: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            if ((userId != null) or (userId?.isNotEmpty() == true)) {
                addPondLogUseCase(userId, _state.value.toPond())
            } else {
                addPondLogUseCase(getLocalUserId(), _state.value.toPond())
            }
        }
    }

    private fun getLocalUserId(): String {
        return "local-${UUID.randomUUID()}"
    }
}
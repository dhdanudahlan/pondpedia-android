package com.pondpedia.compose.pondpedia.presentation.screens.home.ponds.components.viewmodel

import com.pondpedia.compose.pondpedia.domain_old.model.ponds.Pond
import com.pondpedia.compose.pondpedia.domain.model.ponds.PondDummyGenerator.getDummyPond
import com.pondpedia.compose.pondpedia.domain.model.ponds.PondFeed
import com.pondpedia.compose.pondpedia.domain.model.ponds.PondFish
import com.pondpedia.compose.pondpedia.domain.model.ponds.PondGrowthPrediction
import com.pondpedia.compose.pondpedia.domain.model.ponds.PondWater
import com.pondpedia.compose.pondpedia.domain.model.ponds.PondWaterPrediction
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


data class PondState(
    val selectedTabIndex: Int = 0,
    val pondCreateTabList: List<String> = listOf("Kolam", "Ikan", "Air", "Pakan"),
    var pondId: String = getDummyPond().pondId,
    var pondName: String = getDummyPond().pondName,
    var pondLength: Float = getDummyPond().pondLength,
    var pondWidth: Float = getDummyPond().pondWidth,
    var pondDepth: Float = getDummyPond().pondDepth,
    var pondImageUrl: String? = getDummyPond().pondImageUrl,
    var fishId: Int = getDummyPond().pondFish.fishId,
    var fishCommonName: String = getDummyPond().pondFish.fishCommonName,
    var fishScientificName: String = getDummyPond().pondFish.fishScientificName,
    var fishAmount: Int = getDummyPond().pondFish.fishAmount,
    var fishTargetWeight: Float = getDummyPond().pondFish.fishTargetWeight,
    var fishCurrentWeight: Float = getDummyPond().pondFish.fishCurrentWeight,
    var fishCurrentLength: Float = getDummyPond().pondFish.fishCurrentLength,
    var feedId: Int = getDummyPond().pondFeed.feedId,
    var feedName: String = getDummyPond().pondFeed.feedName,
    var feedPercentage: Float = getDummyPond().pondFeed.feedPercentage,
    var feedProtein: Float = getDummyPond().pondFeed.feedProteinPercentage,
    var feedLipid: Float = getDummyPond().pondFeed.feedLipidPercentage,
    var feedCarbohydrate: Float = getDummyPond().pondFeed.feedCarbohydratePercentage,
    var feedOthers: Float = getDummyPond().pondFeed.feedOthersPercentage,
    var feedFrequency: Int = getDummyPond().pondFeed.feedFrequencyDaily,
    var waterTemperature: Float = getDummyPond().pondWater.temperature,
    var waterTurbidity: Float = getDummyPond().pondWater.turbidity,
    var waterDissolvedOxygen: Float = getDummyPond().pondWater.dissolvedOxygen,
    var waterPH: Float = getDummyPond().pondWater.pH,
    var waterAmmonia: Float = getDummyPond().pondWater.ammonia,
    var waterNitrate: Float = getDummyPond().pondWater.nitrate,
    var predWaterTemperature: Float? = getDummyPond().pondWaterPrediction?.temperature,
    var predWaterTurbidity: Float? = getDummyPond().pondWaterPrediction?.turbidity,
    var predWaterDissolvedOxygen: Float? = getDummyPond().pondWaterPrediction?.dissolvedOxygen,
    var predWaterPH: Float? = getDummyPond().pondWaterPrediction?.pH,
    var predWaterAmmonia: Float? = getDummyPond().pondWaterPrediction?.ammonia,
    var predWaterNitrate: Float? = getDummyPond().pondWaterPrediction?.nitrate,
    var predGrowthLength: Float? = getDummyPond().pondGrowthPrediction?.growthLength,
    var predGrowthFeed: Float? = getDummyPond().pondGrowthPrediction?.growthFeed,
    var predGrowthDays: Float? = getDummyPond().pondGrowthPrediction?.growthDays,
    var createdAt: String = getDummyPond().createdAt,
) {
    fun toPond(): Pond {
        return Pond(
            pondId = pondId,
            pondName = pondName,
            pondLength = pondLength,
            pondWidth = pondWidth,
            pondDepth = pondDepth,
            pondImageUrl = pondImageUrl,
            pondFish = PondFish(
                fishId = fishId,
                fishCommonName = fishCommonName,
                fishScientificName = fishScientificName,
                fishAmount = fishAmount,
                fishTargetWeight = fishTargetWeight,
                fishCurrentWeight = fishCurrentWeight,
                fishCurrentLength = fishCurrentLength,
            ),
            pondFeed = PondFeed(
                feedId = feedId,
                feedName = feedName,
                feedPercentage = feedPercentage, 
                feedProteinPercentage = feedProtein,
                feedLipidPercentage = feedLipid,
                feedCarbohydratePercentage = feedCarbohydrate,
                feedOthersPercentage = feedOthers,
                feedFrequencyDaily = feedFrequency,
            ),
            pondWater = PondWater(
                temperature = waterTemperature,
                turbidity = waterTurbidity,
                dissolvedOxygen = waterDissolvedOxygen,
                pH = waterPH,
                ammonia = waterAmmonia,
                nitrate = waterNitrate,
            ),
            pondWaterPrediction = PondWaterPrediction(
                temperature = predWaterTemperature,
                turbidity = predWaterTurbidity,
                dissolvedOxygen = predWaterDissolvedOxygen,
                pH = predWaterPH,
                ammonia = predWaterAmmonia,
                nitrate = predWaterNitrate,
            ),
            pondGrowthPrediction = PondGrowthPrediction(
                growthLength = predGrowthLength,
                growthFeed = predGrowthFeed,
                growthDays = predGrowthDays
            ),
            createdAt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now()),
        )
    }
}
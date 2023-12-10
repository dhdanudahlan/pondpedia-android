package com.pondpedia.android.pondpedia.domain.model.pond_management.new_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pondpedia.android.pondpedia.data.local.entity.pond_management.new_model.WaterEnvironmentalRecordsEntity
import java.time.LocalDateTime
import java.time.LocalTime

data class WaterEnvironmentalRecords(

    val recordId: Long = 0,

    val date: String,

    val pH: Float? = null, // Good: 6.8-7.8 | Bad: < 6.5 or > 8.5
    val temperature: Float? = null, // Good: 20-28 | Bad: < 18 or > 32 | Celcius
    val waterLevel: Float? = null, // cm | Water depth in the pond | Bad: Too low or too high | cm
    val waterColor: String? = null, // Good: Clear | Bad: Cloudy, greenish, brown
    val weather: String? = null, // Good: Sunny, cloudy, rainy | Bad: Extreme weather events

    val dissolvedOxygen: Float? = null, // Good: 6.0-10.0 | Bad: < 5.0 or > 12.0 | mg/L
    val salinity: Float? = null, // Good: 0-20 (freshwater fish and shrimp), 10-35 (brackish water species) | Bad: Outside recommended range | ppt
    val turbidity: Float? = null, // Good: 0-3 | Bad: > 5 | cm/NTU

    val clarity: Float? = null, // Depends on specific context | cm
    val orp: Float? = null, // Varies depending on water conditions | mV

    val doPercentage: Float? = null, // % | Percentage saturation of dissolved oxygen | Bad: Below optimal levels | %
    val electricalConductivity: Float? = null, // μS | Conductivity of water | Varies depending on water composition | μS

    val note: String = "",

    val pondId: Long = 0,
) {
    fun toWaterEnvironmentRecordsEntity(): WaterEnvironmentalRecordsEntity {
        return WaterEnvironmentalRecordsEntity(
            recordId = recordId,
            date = date,
            pH = pH,
            temperature = temperature,
            waterLevel = waterLevel,
            waterColor = waterColor,
            weather = weather,
            dissolvedOxygen = dissolvedOxygen,
            salinity = salinity,
            turbidity = turbidity,
            clarity = clarity,
            orp = orp,
            doPercentage = doPercentage,
            electricalConductivity = electricalConductivity,
            note = note,
            pondId = pondId
        )
    }
}

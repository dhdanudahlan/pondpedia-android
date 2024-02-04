package com.pondpedia.android.pondpedia.domain.model.pond_management

import com.pondpedia.android.pondpedia.data.local.entity.pond_management.WaterRecordsEntity
import com.pondpedia.android.pondpedia.data.remote.dto.pond.water.WaterRequest

data class WaterRecords(

    val recordId: Long,

    val pondId: Long,
    val date: String,

    val waterHeight: Int? = null,
    val weather: String? = null, // Good: Sunny, cloudy, rainy | Bad: Extreme weather events
    val color: String? = null,
    val pH: Double? = null, // Good: 6.8-7.8 | Bad: < 6.5 or > 8.5
    val temperature: Double? = null,  // Good: 20-28 | Bad: < 18 or > 32 | Celcius
    val dissolvedOxygen: Double? = null, // Good: 6.0-10.0 | Bad: < 5.0 or > 12.0 | mg/L
    val salinity: Double? = null, // Good: 0-20 (freshwater fish and shrimp), 10-35 (brackish water species) | Bad: Outside recommended range | ppt
    val turbidity: Double? = null,// Good: 0-3 | Bad: > 5 | cm/NTU
    val ammonia: Double? = null,
    val nitrite: Double? = null,
    val alkalinity: Double? = null,
    val note: String? = null,
) {
    fun toWaterRecordsEntity(): WaterRecordsEntity {
        return WaterRecordsEntity(
            recordId = recordId,
            pondId = pondId,
            date = date,
            waterHeight = waterHeight,
            pH = pH,
            temperature = temperature,
            weather = weather,
            dissolvedOxygen = dissolvedOxygen,
            salinity = salinity,
            turbidity = turbidity,
            ammonia = ammonia,
            nitrite = nitrite,
            alkalinity = alkalinity,
            color = color,
            note = note,
        )
    }

    fun toWaterRequest(): WaterRequest {
        return WaterRequest(
            waterHeight = waterHeight,
            waterRecord = date,
            weather = weather,
            color = color,
            pH = pH,
            temperature = temperature,
            dissolvedOxygen = dissolvedOxygen,
            salinity = salinity,
            turbidity = turbidity,
            alkalinity = alkalinity,
            note = note,
        )
    }
}

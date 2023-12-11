package com.pondpedia.android.pondpedia.domain.model.pond_management

import com.pondpedia.android.pondpedia.data.local.entity.pond_management.WaterRecordsEntity
import java.time.LocalDateTime

data class WaterRecords(

    val recordId: Long,

    val date: String,

    val level: Int = 0,

    val pH: Float? = null, // Good: 6.8-7.8 | Bad: < 6.5 or > 8.5
    val temperature: Float? = null, // Good: 20-28 | Bad: < 18 or > 32 | Celcius
    val weather: String = "Cerah", // Good: Sunny, cloudy, rainy | Bad: Extreme weather events

    val dissolvedOxygen: Float? = null, // Good: 6.0-10.0 | Bad: < 5.0 or > 12.0 | mg/L
    val salinity: Float? = null, // Good: 0-20 (freshwater fish and shrimp), 10-35 (brackish water species) | Bad: Outside recommended range | ppt
    val turbidity: Float? = null, // Good: 0-3 | Bad: > 5 | cm/NTU

    val clarity: Float? = null, // Depends on specific context | cm

    val color: String,

    val note: String,

    val pondId: Long,
) {
    fun toWaterRecordsEntity(): WaterRecordsEntity {
        return WaterRecordsEntity(
            recordId = recordId,
            date = date,
            level = level,
            pH = pH,
            temperature = temperature,
            weather = weather,
            dissolvedOxygen = dissolvedOxygen,
            salinity = salinity,
            turbidity = turbidity,
            clarity = clarity,
            color = color,
            note = note,
            pondId = pondId
        )
    }
}

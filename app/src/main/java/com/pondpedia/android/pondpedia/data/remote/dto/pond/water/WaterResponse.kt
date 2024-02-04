package com.pondpedia.android.pondpedia.data.remote.dto.pond.water

import com.google.gson.annotations.SerializedName
import com.pondpedia.android.pondpedia.domain.model.pond_management.WaterRecords

data class WaterResponse(
    val id: Long,
    @field:SerializedName("pond")
    val pond: List<Long>,

    val waterHeight: Int?,
    val waterRecord: String,
    val weather: String?,
    val color: String?,
    val pH: Double?,
    val temperature: Double?,
    val dissolvedOxygen: Double?,
    val salinity: Double?,
    val turbidity: Double?,
    val ammonia: Double?,
    val nitrite: Double?,
    val alkalinity: Double?,
    val note: String?,

    val updatedAt: String,
    val createdAt: String,
) {
    fun toWaterRecords(): WaterRecords {
        return WaterRecords(
            recordId = id,
            pondId = pond.first(),
            date = waterRecord,
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
}

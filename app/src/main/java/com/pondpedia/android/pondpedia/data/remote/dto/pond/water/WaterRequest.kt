package com.pondpedia.android.pondpedia.data.remote.dto.pond.water

data class WaterRequest(
    val waterHeight: Int? = null,
    val waterRecord: String,
    val weather: String? = null,
    val color: String? = null,
    val pH: Double? = null,
    val temperature: Double? = null,
    val dissolvedOxygen: Double? = null,
    val salinity: Double? = null,
    val turbidity: Double? = null,
    val alkalinity: Double? = null,
    val note: String? = null
)

package com.pondpedia.android.pondpedia.data.remote.dto.pond

import com.google.gson.annotations.SerializedName

data class PondRequest(
    val name: String,
    val user: String,
    val area: Int,
    val depth: Int,
    @field:SerializedName("pond_type")
    val pondType: String,
    @field:SerializedName("water_type")
    val waterType: String,
    val location: String,
    val description: String,
)

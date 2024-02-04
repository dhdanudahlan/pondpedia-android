package com.pondpedia.android.pondpedia.data.remote.dto.base

import com.google.gson.annotations.SerializedName

data class BaseSuccess<T>(
    val success: Boolean,
    @field:SerializedName(value = "data", alternate = ["waterData"])
    val data: T,
    val message: String
)

package com.pondpedia.android.pondpedia.data.remote.dto.base

data class BaseSuccess<T>(
    val success: Boolean,
    val data: T,
    val message: String
)

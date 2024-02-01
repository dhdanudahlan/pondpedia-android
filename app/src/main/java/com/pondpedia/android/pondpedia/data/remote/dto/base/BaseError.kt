package com.pondpedia.android.pondpedia.data.remote.dto.base

data class BaseError(
    val errors: List<Error>
)

data class Error(
    val name: String,
    val data: List<Data>,
    val message: String
)

data class Data(
    val field: String,
    val message: String
)
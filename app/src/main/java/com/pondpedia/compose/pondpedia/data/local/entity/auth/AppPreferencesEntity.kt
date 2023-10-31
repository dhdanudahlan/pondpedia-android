package com.pondpedia.compose.pondpedia.data.local.entity.auth

import androidx.room.PrimaryKey

data class AppPreferencesEntity(
    @PrimaryKey(autoGenerate = false)
    val preference: String,
    val value: String
)

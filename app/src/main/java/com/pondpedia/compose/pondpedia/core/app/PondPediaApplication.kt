package com.pondpedia.compose.pondpedia.core.app

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PondPediaApplication: Application() {
    val dataStore: DataStore<Preferences> by preferencesDataStore(name = "pondpedia_datastore")
    lateinit var pondPediaDataStore: PondPediaDataStore

    override fun onCreate() {
        super.onCreate()
        pondPediaDataStore = PondPediaDataStore(dataStore)
    }
}
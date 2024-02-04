package com.pondpedia.android.pondpedia.core.app

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PondPediaApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
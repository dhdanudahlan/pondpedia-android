package com.pondpedia.android.pondpedia.core.app

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

class PondPediaDataStore(private val dataStore: DataStore<Preferences>) {
    private object FarmerPreferencesKeys {
        val farmerId = stringPreferencesKey("farmer_id")
        val farmerName = stringPreferencesKey("farmer_name")
        val farmerPhoneNumber = stringPreferencesKey("farmer_picture_url")
        val farmerEmail = stringPreferencesKey("farmer_email")
    }

}
package com.pondpedia.android.pondpedia.core.util.manager

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.pondpedia.android.pondpedia.di.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * For now one user can be only have one thread due to the requirement of the app
 * If the requirement change, this class can be deleted to handle multiple thread
 */
class ThreadManager(private val context: Context) {

    companion object {
        private val THREAD_ID_KEY = stringPreferencesKey("thread_id")
    }

    fun getThreadId(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[THREAD_ID_KEY]
        }
    }

    suspend fun saveThreadId(threadId: String) {
        context.dataStore.edit { preferences ->
            preferences[THREAD_ID_KEY] = threadId
        }
    }
}
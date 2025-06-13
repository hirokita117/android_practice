package com.example.mydiary.shared.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(private val dataStore: DataStore<Preferences>) {

    companion object {
        val PIN_HASH_KEY = stringPreferencesKey("pin_hash")
        val APP_THEME_KEY = stringPreferencesKey("app_theme") // Store theme name as String, e.g., "LIGHT", "DARK", "SYSTEM"
    }

    val pinHashFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[PIN_HASH_KEY]
    }

    suspend fun savePinHash(pinHash: String) {
        dataStore.edit { preferences ->
            preferences[PIN_HASH_KEY] = pinHash
        }
    }

    suspend fun clearPinHash() {
        dataStore.edit { preferences ->
            preferences.remove(PIN_HASH_KEY)
        }
    }

    val appThemeFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[APP_THEME_KEY]
    }

    suspend fun saveAppTheme(themeName: String) {
        dataStore.edit { preferences ->
            preferences[APP_THEME_KEY] = themeName
        }
    }
}

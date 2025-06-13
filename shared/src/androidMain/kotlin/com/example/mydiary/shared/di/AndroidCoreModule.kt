package com.example.mydiary.shared.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.mydiary.shared.data.local.MyDiaryDatabase
import com.example.mydiary.shared.data.preferences.UserPreferencesRepository
import com.example.mydiary.shared.repository.DiaryRepository
import com.example.mydiary.shared.repository.DiaryRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val MYDIARY_DATASTORE_NAME = "mydiary_preferences"

val androidCoreModule = module {
    // Database
    single<MyDiaryDatabase> {
        Room.databaseBuilder(
            androidContext(),
            MyDiaryDatabase::class.java,
            "mydiary.db" // Database file name
        ).build()
    }

    // DAO
    single { get<MyDiaryDatabase>().diaryDao() }

    // DataStore
    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create(
            produceFile = { androidContext().preferencesDataStoreFile(MYDIARY_DATASTORE_NAME) }
        )
    }

    // UserPreferencesRepository
    single { UserPreferencesRepository(get()) }

    // DiaryRepository
    single<DiaryRepository> { DiaryRepositoryImpl(get()) }
}

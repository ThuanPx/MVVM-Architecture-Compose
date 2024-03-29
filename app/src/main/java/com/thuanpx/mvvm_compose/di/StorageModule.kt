package com.thuanpx.mvvm_compose.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.squareup.moshi.Moshi
import com.thuanpx.mvvm_compose.data.local.datastore.PreferenceDataStore
import com.thuanpx.mvvm_compose.data.local.datastore.PreferenceDataStoreDefault
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ThuanPx on 16/09/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    private const val DATA_STORE_FILE_NAME = "app_prefs.pb"

    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            produceFile = { appContext.preferencesDataStoreFile(DATA_STORE_FILE_NAME) }
        )
    }

    @Singleton
    @Provides
    fun providePreferenceDataStore(
        dataStore: DataStore<Preferences>,
        moshi: Moshi
    ): PreferenceDataStore {
        return PreferenceDataStoreDefault(dataStore, moshi)
    }

}

package com.multimodule.protodatasyore.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.multimodule.protodatasyore.Preferences
import com.multimodule.protodatasyore.Session
import com.multimodule.protodatasyore.factory.preferencesDataStore
import com.multimodule.protodatasyore.factory.sessionDataStore
import com.multimodule.protodatasyore.manager.preferences.PreferencesDataStoreImplementer
import com.multimodule.protodatasyore.manager.preferences.PreferencesDataStoreInterface
import com.multimodule.protodatasyore.manager.session.SessionDataStoreImplementer
import com.multimodule.protodatasyore.manager.session.SessionDataStoreInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideSessionDataStore(@ApplicationContext context: Context): DataStore<Session> {
        return context.sessionDataStore
    }

    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.preferencesDataStore
    }

    @Provides
    @Singleton
    fun provideSessionStoreManager(sessionDataStore: DataStore<Session>): SessionDataStoreInterface {
        return SessionDataStoreImplementer(sessionDataStore)
    }

    @Provides
    @Singleton
    fun providePreferencesStoreManager(preferencesDataStore: DataStore<Preferences>): PreferencesDataStoreInterface {
        return PreferencesDataStoreImplementer(preferencesDataStore)
    }
}
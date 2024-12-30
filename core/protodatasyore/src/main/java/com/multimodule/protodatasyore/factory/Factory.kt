package com.multimodule.protodatasyore.factory

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.multimodule.protodatasyore.Preferences
import com.multimodule.protodatasyore.Session
import com.multimodule.protodatasyore.serializer.PreferencesSerializer
import com.multimodule.protodatasyore.serializer.SessionSerializer

val Context.sessionDataStore: DataStore<Session> by dataStore(
    fileName = "session.pb",
    serializer = SessionSerializer
)

val Context.preferencesDataStore: DataStore<Preferences> by dataStore(
    fileName = "preferences.pb",
    serializer = PreferencesSerializer
)
package com.multimodule.protodatasyore.manager.session

import com.multimodule.protodatasyore.Session
import kotlinx.coroutines.flow.Flow

interface SessionDataStoreInterface {
    // setter functions
    // setter functions

    suspend fun setAccessToken(accessToken: String)
    suspend fun setRefreshToken(refreshToken: String)
    suspend fun setUserIdToken(userId: String)

    suspend fun setSession(accessToken: String, refreshToken: String, userId: String)

    // getters

    suspend fun getAccessToken(): String
    fun getAccessTokenFlow(): Flow<String>
    suspend fun getRefreshToken(): String
    fun getRefreshTokenFlow(): Flow<String>
    suspend fun getUserId(): String
    fun getUserIdFlow(): Flow<String>

    suspend fun getSession(): Session
    fun getSessionFlow(): Flow<Session>

}
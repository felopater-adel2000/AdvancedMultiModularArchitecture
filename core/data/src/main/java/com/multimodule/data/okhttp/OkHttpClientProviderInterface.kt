package com.multimodule.data.okhttp

import okhttp3.OkHttpClient

interface OkHttpClientProviderInterface {
    fun getOkHttpClient(pin: String): OkHttpClient.Builder

    fun cancelAllRequests()
}

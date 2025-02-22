package com.multimodule.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale

// Headers
const val AUTHORIZATION_HEADER = "Authorization"
const val ACCEPT_HEADER = "Accept"
const val CONTENT_TYPE_HEADER = "Content-Type"
const val ACCEPT_LANGUAGE_HEADER = "Accept-Language"
const val CLIENT_ID_HEADER = "Client-Id"
const val BEARER = "Bearer "

// Values
const val JSON = "application/json"
const val ARABIC_LANGUAGE = "ar"
const val ENGLISH_LANGUAGE = "en"

class HeaderInterceptor(
    private val clientId: String,
    private val languageProvider: () -> Locale,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val language = if (languageProvider.invoke() == Locale.ENGLISH) ENGLISH_LANGUAGE else ARABIC_LANGUAGE

        val request = chain.request()
        val builder = request.newBuilder()
            .addHeader(CLIENT_ID_HEADER, clientId)
            .addHeader(ACCEPT_HEADER, JSON)
            .addHeader(CONTENT_TYPE_HEADER, JSON)
            .addHeader(ACCEPT_LANGUAGE_HEADER, language)


        return chain.proceed(builder.build())
    }
}

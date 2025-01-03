package com.multimodule.data.di

import com.multimodule.data.BuildConfig
import com.multimodule.data.constants.ACCESS_TOKEN_TAG
import com.multimodule.data.constants.CLIENT_ID_TAG
import com.multimodule.data.constants.HEADER_INTERCEPTOR_TAG
import com.multimodule.data.constants.LANGUAGE_TAG
import com.multimodule.data.constants.LOGGING_INTERCEPTOR_TAG
import com.multimodule.data.interceptor.AUTHORIZATION_HEADER
import com.multimodule.data.interceptor.CLIENT_ID_HEADER
import com.multimodule.data.interceptor.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import java.util.Locale
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InterceptorsModule {

    @Provides
    @Singleton
    @Named(HEADER_INTERCEPTOR_TAG)
    fun provideHeaderInterceptor(
        @Named(CLIENT_ID_TAG) clientId: String,
        @Named(ACCESS_TOKEN_TAG) accessTokenProvider: () -> String?,
        @Named(LANGUAGE_TAG) languageProvider: () -> Locale,
    ): Interceptor {
        return HeaderInterceptor(
            clientId,
            accessTokenProvider,
            languageProvider,
        )
    }

    // Http Logging Interceptor
    @Provides
    @Singleton
    @Named(LOGGING_INTERCEPTOR_TAG)
    fun provideOkHttpLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        if (!BuildConfig.DEBUG) {
            interceptor.redactHeader(CLIENT_ID_HEADER) // redact any header that contains sensitive data.
            interceptor.redactHeader(AUTHORIZATION_HEADER) // redact any header that contains sensitive data.
        }

        return interceptor
    }
}

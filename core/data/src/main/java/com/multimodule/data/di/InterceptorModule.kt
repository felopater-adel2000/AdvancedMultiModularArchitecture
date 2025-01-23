package com.multimodule.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.multimodule.data.BuildConfig
import com.multimodule.data.constants.ACCESS_TOKEN_TAG
import com.multimodule.data.constants.AUTHENTICATION_INTERCEPTOR_TAG
import com.multimodule.data.constants.CHUCKER_INTERCEPTOR_TAG
import com.multimodule.data.constants.CLIENT_ID_TAG
import com.multimodule.data.constants.DISPATCHER_IO_TAG
import com.multimodule.data.constants.HEADER_INTERCEPTOR_TAG
import com.multimodule.data.constants.LANGUAGE_TAG
import com.multimodule.data.constants.LOGGING_INTERCEPTOR_TAG
import com.multimodule.data.interceptor.AUTHORIZATION_HEADER
import com.multimodule.data.interceptor.AuthenticationInterceptor
import com.multimodule.data.interceptor.CLIENT_ID_HEADER
import com.multimodule.data.interceptor.HeaderInterceptor
import com.multimodule.protodatasyore.manager.session.SessionDataStoreInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
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

    @Provides
    @Singleton
    @Named(AUTHENTICATION_INTERCEPTOR_TAG)
    fun provideAuthenticationInterceptor(
        sessionDataStoreInterface: SessionDataStoreInterface,
        @Named(DISPATCHER_IO_TAG) coroutineDispatcher: CoroutineDispatcher,
    ): Interceptor {
        return AuthenticationInterceptor(
            sessionDataStoreInterface,
            coroutineDispatcher,
        )
    }

    @Provides
    @Singleton
    @Named(CHUCKER_INTERCEPTOR_TAG)
    fun provideChuckerInterceptor(@ApplicationContext context: Context): Interceptor {
        return ChuckerInterceptor.Builder(context)
            // The previously created Collector
            .collector(
                ChuckerCollector(
                    context = context,
                    showNotification = true,
                    retentionPeriod = RetentionManager.Period.ONE_HOUR,
                ),
            )
            // The max body content length in bytes, after this responses will be truncated.
            .maxContentLength(250_000L)
            // List of headers to replace with ** in the Chucker UI
            .redactHeaders(AUTHORIZATION_HEADER)
            // Read the whole response body even when the client does not consume the response completely.
            // This is useful in case of parsing errors or when the response body
            // is closed before being read like in Retrofit with Void and Unit types.
            .alwaysReadResponseBody(true)
            // Use decoder when processing request and response bodies. When multiple decoders are installed they
            // are applied in an order they were added.
            // Controls Android shortcut creation.
            .createShortcut(true)
            .build()
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

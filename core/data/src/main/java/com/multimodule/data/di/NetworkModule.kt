package com.multimodule.data.di

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.multimodule.data.BuildConfig
import com.multimodule.data.OkHttpClientProvider
import com.multimodule.data.connectivity.NetworkMonitorImplementer
import com.multimodule.data.connectivity.NetworkMonitorInterface
import com.multimodule.data.constants.ACCESS_TOKEN_TAG
import com.multimodule.data.constants.CHUCKER_INTERCEPTOR_TAG
import com.multimodule.data.constants.CLIENT_ID_TAG
import com.multimodule.data.constants.HEADER_INTERCEPTOR_TAG
import com.multimodule.data.constants.LANGUAGE_TAG
import com.multimodule.data.constants.LOGGING_INTERCEPTOR_TAG
import com.multimodule.data.factory.ServiceFactory
import com.multimodule.data.interceptor.AUTHORIZATION_HEADER
import com.multimodule.data.interceptor.CLIENT_ID_HEADER
import com.multimodule.data.interceptor.HeaderInterceptor
import com.multimodule.data.okhttp.OkHttpClientProviderInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideNetworkMonitor(context: Context): NetworkMonitorInterface {
        return NetworkMonitorImplementer(context)
    }



    @Provides
    @Singleton
    fun provideOkHttpClientProvider(): OkHttpClientProviderInterface {
        return OkHttpClientProvider()
    }

    // ok http factory
    @Provides
    @Singleton
    fun provideOkHttpCallFactory(
        @Named(LOGGING_INTERCEPTOR_TAG) okHttpLoggingInterceptor: Interceptor,
        @Named(HEADER_INTERCEPTOR_TAG) headerInterceptor: Interceptor,
        @Named(CHUCKER_INTERCEPTOR_TAG) chuckerInterceptor: Interceptor,
        okHttpClientProvider: OkHttpClientProviderInterface
    ): OkHttpClient {
        return okHttpClientProvider.getOkHttpClient(BuildConfig.PIN_CERTIFCATE)
            .addInterceptor(okHttpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .addInterceptor(chuckerInterceptor)
            .retryOnConnectionFailure(true)
            .followRedirects(false)
            .followSslRedirects(false)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl("")
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideServiceFactory(retrofit: Retrofit): ServiceFactory {
        return ServiceFactory(retrofit)
    }
}

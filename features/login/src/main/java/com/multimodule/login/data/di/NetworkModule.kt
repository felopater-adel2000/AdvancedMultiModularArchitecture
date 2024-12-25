package com.multimodule.login.data.di

import com.google.gson.Gson
import com.multimodule.data.connectivity.NetworkMonitorInterface
import com.multimodule.data.constants.USER_ID_TAG
import com.multimodule.data.factory.ServiceFactory
import com.multimodule.data.source.NetworkDataSource
import com.multimodule.login.data.servise.LoginService
import com.multimodule.login.data.source.LoginRemote
import com.multimodule.login.data.source.LoginRemoteImplementer
import com.multimodule.login.domain.mapper.LoginMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideLoginServiceFactory(serviceFactory: ServiceFactory): LoginService {
        return serviceFactory.create(LoginService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkDataSource(
        loginService: LoginService,
        gson: Gson,
        networkMonitorInterface: NetworkMonitorInterface,
        @Named(USER_ID_TAG) userIdProvider: () -> String
    ): NetworkDataSource<LoginService> {
        return NetworkDataSource(loginService, gson, networkMonitorInterface, userIdProvider)
    }


    @Provides
    @Singleton
    fun provideLoginRemoteImplementer(
        networkDataSource: NetworkDataSource<LoginService>,
        loginMapper: LoginMapper,
    ): LoginRemote {
        return LoginRemoteImplementer(networkDataSource, loginMapper)
    }
}
package com.multimodule.navigator.di

import com.multimodule.navigator.core.AppNavigator
import com.multimodule.navigator.core.AppNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigatorModule {
    @Provides
    @Singleton
    fun provideNavigator(): AppNavigator {
        return AppNavigatorImpl()
    }
}
package com.apps.tilak.airlines.di

import com.apps.tilak.airlines.presentation.common.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoggerModule {

    @Singleton
    @Provides
    fun provideLogger(): Logger {
        return Logger()
    }
}
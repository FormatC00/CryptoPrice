package com.github.formatc00.di.module

import android.app.Application
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {
    
    @JvmStatic
    @Provides
    @Singleton
    fun provideContext(application: Application) = application.applicationContext
    
    @JvmStatic
    @Provides
    @Singleton
    fun provideGson() = Gson()
}
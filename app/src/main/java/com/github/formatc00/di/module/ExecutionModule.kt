package com.github.formatc00.di.module

import com.github.formatc00.di.qualifier.execution.Background
import com.github.formatc00.di.qualifier.execution.Foreground
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
object ExecutionModule {
    
    @JvmStatic
    @Provides
    @Background
    @Singleton
    fun provideBackgroundScheduler() = Schedulers.computation()
    
    @JvmStatic
    @Provides
    @Foreground
    @Singleton
    fun provideForegroundScheduler() = AndroidSchedulers.mainThread()
}
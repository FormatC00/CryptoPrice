package com.github.formatc00.di.module

import com.github.formatc00.di.module.activity.HomeActivityFragmentProvider
import com.github.formatc00.di.module.activity.HomeActivityModule
import com.github.formatc00.di.scope.ActivityScope
import com.github.formatc00.ui.activity.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {
    
    @ActivityScope
    @ContributesAndroidInjector(modules = [
        HomeActivityFragmentProvider::class,
        HomeActivityModule::class,
        NavigationModule::class
    ])
    abstract fun contributesHomeActivity(): HomeActivity
}
package com.github.formatc00.di.module

import com.github.formatc00.di.scope.ActivityScope
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
class NavigationModule {
    
    private val cicerone = Cicerone.create(Router())
    
    @Provides
    @ActivityScope
    fun provideRouter(): Router = cicerone.router
    
    @Provides
    @ActivityScope
    fun provideNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder
}
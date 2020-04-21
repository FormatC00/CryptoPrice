package com.github.formatc00.di.module.activity

import com.github.formatc00.di.qualifier.execution.Background
import com.github.formatc00.di.qualifier.execution.Foreground
import com.github.formatc00.di.scope.ActivityScope
import com.github.formatc00.mvp.presenter.HomeActivityPresenter
import com.github.formatc00.ui.activity.HomeActivity
import com.github.formatc00.ui.dialog.AlertMessageInterface
import com.github.formatc00.ui.navigation.HomeActivityNavigator
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import ru.terrakok.cicerone.Router

@Module
class HomeActivityModule {
    
    @ActivityScope
    @Provides
    fun provideMessageInterface(activity: HomeActivity) = AlertMessageInterface(activity)
    
    @ActivityScope
    @Provides
    fun provideNavigator(activity: HomeActivity) = HomeActivityNavigator(activity)
    
    @ActivityScope
    @Provides
    fun providePresenter(
        @Background backgroundScheduler: Scheduler,
        @Foreground foregroundScheduler: Scheduler,
        router: Router
    ) = HomeActivityPresenter(backgroundScheduler, foregroundScheduler, router)
    
    @ActivityScope
    @Provides
    fun provideActivity(activity: HomeActivity) = activity
}

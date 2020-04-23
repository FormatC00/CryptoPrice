package com.github.formatc00.di.module.activity

import android.app.Activity
import com.github.formatc00.core.facade.CryptoFacade
import com.github.formatc00.di.qualifier.execution.Background
import com.github.formatc00.di.qualifier.execution.Foreground
import com.github.formatc00.di.scope.ActivityScope
import com.github.formatc00.mvp.base.MessageInterface
import com.github.formatc00.mvp.contract.HomeActivityContract
import com.github.formatc00.mvp.presenter.HomeActivityPresenter
import com.github.formatc00.ui.activity.HomeActivity
import com.github.formatc00.ui.dialog.AlertMessageInterface
import com.github.formatc00.ui.navigation.BaseNavigator
import com.github.formatc00.ui.navigation.HomeActivityNavigator
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import ru.terrakok.cicerone.Router

@Module
class HomeActivityModule {
    
    @ActivityScope
    @Provides
    fun provideMessageInterface(activity: HomeActivity): MessageInterface =
        AlertMessageInterface(activity)
    
    @ActivityScope
    @Provides
    fun provideNavigator(activity: HomeActivity): BaseNavigator = HomeActivityNavigator(activity)
    
    @ActivityScope
    @Provides
    fun providePresenter(
        @Background backgroundScheduler: Scheduler,
        @Foreground foregroundScheduler: Scheduler,
        router: Router,
        cryptoFacade: CryptoFacade
    ): HomeActivityContract.Presenter =
        HomeActivityPresenter(backgroundScheduler, foregroundScheduler, router, cryptoFacade)
    
    @ActivityScope
    @Provides
    fun provideActivity(activity: HomeActivity): Activity = activity
}

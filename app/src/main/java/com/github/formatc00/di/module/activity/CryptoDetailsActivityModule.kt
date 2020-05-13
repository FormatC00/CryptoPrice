package com.github.formatc00.di.module.activity

import android.app.Activity
import com.github.formatc00.di.qualifier.execution.Background
import com.github.formatc00.di.qualifier.execution.Foreground
import com.github.formatc00.di.scope.ActivityScope
import com.github.formatc00.mvp.base.MessageInterface
import com.github.formatc00.mvp.contract.CryptoDetailsActivityContract
import com.github.formatc00.mvp.presenter.CryptoDetailsActivityPresenter
import com.github.formatc00.ui.activity.CryptoDetailsActivity
import com.github.formatc00.ui.dialog.AlertMessageInterface
import com.github.formatc00.ui.navigation.BaseNavigator
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import ru.terrakok.cicerone.Router

@Module
class CryptoDetailsActivityModule {
    
    @ActivityScope
    @Provides
    fun provideMessageInterface(activity: CryptoDetailsActivity): MessageInterface =
        AlertMessageInterface(activity)
    
    @ActivityScope
    @Provides
    fun provideNavigator(activity: CryptoDetailsActivity): BaseNavigator = BaseNavigator(activity)
    
    @ActivityScope
    @Provides
    fun providePresenter(
        @Background backgroundScheduler: Scheduler,
        @Foreground foregroundScheduler: Scheduler,
        router: Router
    ): CryptoDetailsActivityContract.Presenter =
        CryptoDetailsActivityPresenter(backgroundScheduler, foregroundScheduler, router)
    
    @ActivityScope
    @Provides
    fun provideActivity(activity: CryptoDetailsActivity): Activity = activity
}
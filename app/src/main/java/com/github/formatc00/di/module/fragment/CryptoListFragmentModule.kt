package com.github.formatc00.di.module.fragment

import com.github.formatc00.di.qualifier.execution.Background
import com.github.formatc00.di.qualifier.execution.Foreground
import com.github.formatc00.di.scope.FragmentScope
import com.github.formatc00.mvp.contract.CryptoListFragmentContract
import com.github.formatc00.mvp.presenter.CryptoListFragmentPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import ru.terrakok.cicerone.Router

@Module
class CryptoListFragmentModule {
    
    @FragmentScope
    @Provides
    fun providePresenter(
        @Background backgroundScheduler: Scheduler,
        @Foreground foregroundScheduler: Scheduler,
        router: Router
    ): CryptoListFragmentContract.Presenter =
        CryptoListFragmentPresenter(backgroundScheduler, foregroundScheduler, router)
}

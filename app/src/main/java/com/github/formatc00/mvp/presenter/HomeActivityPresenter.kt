package com.github.formatc00.mvp.presenter

import com.github.formatc00.mvp.base.BasePresenter
import com.github.formatc00.mvp.contract.HomeActivityContract
import com.github.formatc00.ui.navigation.Screens
import io.reactivex.Scheduler
import ru.terrakok.cicerone.Router

class HomeActivityPresenter(
    backgroundScheduler: Scheduler,
    foregroundScheduler: Scheduler,
    router: Router
) : BasePresenter<HomeActivityContract.View>(backgroundScheduler, foregroundScheduler, router),
    HomeActivityContract.Presenter {
    
    override fun onFirstAttach() {
        super.onFirstAttach()
        router.newRootScreen(Screens.CryptoListScreen())
    }
}
package com.github.formatc00.mvp.presenter

import com.github.formatc00.core.facade.CryptoFacade
import com.github.formatc00.mvp.base.BasePresenter
import com.github.formatc00.mvp.contract.HomeActivityContract
import io.reactivex.Scheduler
import ru.terrakok.cicerone.Router

class HomeActivityPresenter(
    backgroundScheduler: Scheduler,
    foregroundScheduler: Scheduler,
    router: Router,
    cryptoFacade: CryptoFacade
) : BasePresenter<HomeActivityContract.View>(backgroundScheduler, foregroundScheduler, router),
    HomeActivityContract.Presenter
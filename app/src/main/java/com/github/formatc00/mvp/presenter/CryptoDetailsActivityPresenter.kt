package com.github.formatc00.mvp.presenter

import com.github.formatc00.mvp.base.BasePresenter
import com.github.formatc00.mvp.contract.CryptoDetailsActivityContract
import io.reactivex.Scheduler
import ru.terrakok.cicerone.Router

class CryptoDetailsActivityPresenter(
    backgroundScheduler: Scheduler,
    foregroundScheduler: Scheduler,
    router: Router
) : BasePresenter<CryptoDetailsActivityContract.View>(
    backgroundScheduler,
    foregroundScheduler,
    router
), CryptoDetailsActivityContract.Presenter
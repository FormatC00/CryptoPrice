package com.github.formatc00.mvp.presenter

import com.github.formatc00.mvp.base.BasePresenter
import com.github.formatc00.mvp.contract.CryptoListFragmentContract
import io.reactivex.Scheduler
import ru.terrakok.cicerone.Router

class CryptoListFragmentPresenter(
    backgroundScheduler: Scheduler,
    foregroundScheduler: Scheduler,
    router: Router
) : BasePresenter<CryptoListFragmentContract.View>(
    backgroundScheduler,
    foregroundScheduler,
    router
),
    CryptoListFragmentContract.Presenter
package com.github.formatc00.mvp.presenter

import com.github.formatc00.mvp.base.BasePresenter
import com.github.formatc00.mvp.contract.ListFragmentContract
import io.reactivex.Scheduler
import ru.terrakok.cicerone.Router

class ListFragmentPresenter(
    backgroundScheduler: Scheduler,
    foregroundScheduler: Scheduler,
    router: Router
) : BasePresenter<ListFragmentContract.View>(backgroundScheduler, foregroundScheduler, router),
    ListFragmentContract.Presenter
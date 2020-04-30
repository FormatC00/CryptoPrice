package com.github.formatc00.mvp.presenter

import com.github.formatc00.core.facade.CryptoFacade
import com.github.formatc00.mvp.base.BasePresenter
import com.github.formatc00.mvp.contract.CryptoListFragmentContract
import io.reactivex.Scheduler
import ru.terrakok.cicerone.Router

class CryptoListFragmentPresenter(
    backgroundScheduler: Scheduler,
    foregroundScheduler: Scheduler,
    router: Router,
    private val cryptoFacade: CryptoFacade
) : BasePresenter<CryptoListFragmentContract.View>(
    backgroundScheduler,
    foregroundScheduler,
    router
), CryptoListFragmentContract.Presenter {
    
    override fun onFirstAttach() {
        super.onFirstAttach()
        subscribe(cryptoFacade.getCryptocurrenciesMap(), getView()::showData)
    }
}
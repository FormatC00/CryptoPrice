package com.github.formatc00.mvp.presenter

import com.github.formatc00.core.facade.CryptoFacade
import com.github.formatc00.data.network.CRYPTOCURRENCIES_MAP_DEFAULT_OFFSET
import com.github.formatc00.data.network.CRYPTOCURRENCIES_MAP_LIMIT
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
    
    override var isLastPage = false
    
    override var isLoading = false
    
    private var offset = 1
    
    override fun onLoadMoreItems() {
        loadData()
    }
    
    override fun onFirstAttach() {
        super.onFirstAttach()
        loadData()
    }
    
    private fun loadData() {
        showPaginationProgress()
        
        val dataSingle = cryptoFacade.getCryptocurrenciesMap(offset)
            .doOnError { hidePaginationProgress() }
            .doOnDispose { hidePaginationProgress() }
        
        subscribe(dataSingle) {
            hidePaginationProgress()
            isLastPage = it.isEmpty()
            getView().showData(it)
            
            offset += CRYPTOCURRENCIES_MAP_LIMIT
        }
    }
    
    private fun showPaginationProgress() {
        if (offset > CRYPTOCURRENCIES_MAP_DEFAULT_OFFSET) getView().showPaginationProgress()
        isLoading = true
    }
    
    private fun hidePaginationProgress() {
        if (offset > CRYPTOCURRENCIES_MAP_DEFAULT_OFFSET) getView().hidePaginationProgress()
        isLoading = false
    }
}
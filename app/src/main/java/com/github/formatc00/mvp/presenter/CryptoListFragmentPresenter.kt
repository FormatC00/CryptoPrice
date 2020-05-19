package com.github.formatc00.mvp.presenter

import com.github.formatc00.core.entity.Cryptocurrency
import com.github.formatc00.core.facade.CryptoFacade
import com.github.formatc00.data.network.CRYPTOCURRENCIES_MAP_DEFAULT_OFFSET
import com.github.formatc00.data.network.CRYPTOCURRENCIES_MAP_LIMIT
import com.github.formatc00.mvp.base.BasePresenter
import com.github.formatc00.mvp.contract.CryptoListFragmentContract
import com.github.formatc00.ui.navigation.Screens
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
    
    private var offset = CRYPTOCURRENCIES_MAP_DEFAULT_OFFSET
    
    override fun onLoadMoreItems() {
        loadData()
    }
    
    override fun onCryptoItemClick(cryptocurrency: Cryptocurrency) {
        router.navigateTo(Screens.CryptoDetailsScreen(cryptocurrency))
    }
    
    override fun onFirstAttach() {
        super.onFirstAttach()
        loadData()
    }
    
    private fun loadData() {
        showProgress()
        
        val dataSingle = cryptoFacade.getCryptocurrenciesMap(offset)
            .doOnError { hideProgress() }
            .doOnDispose { hideProgress() }
    
        val view = getView()
        subscribe(dataSingle) {
            hideProgress()
            isLastPage = it.isEmpty()
            view.showData(it)
            
            offset += CRYPTOCURRENCIES_MAP_LIMIT
        }
    }
    
    private fun showProgress() {
        if (offset > CRYPTOCURRENCIES_MAP_DEFAULT_OFFSET) {
            getView().showPaginationProgress()
        } else {
            getView().showInitialProgress()
        }
        isLoading = true
    }
    
    private fun hideProgress() {
        if (offset > CRYPTOCURRENCIES_MAP_DEFAULT_OFFSET) {
            getView().hidePaginationProgress()
        } else {
            getView().hideInitialProgress()
        }
        isLoading = false
    }
}
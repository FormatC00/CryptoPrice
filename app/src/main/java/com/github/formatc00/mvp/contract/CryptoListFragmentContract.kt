package com.github.formatc00.mvp.contract

import com.github.formatc00.core.entity.Cryptocurrency
import com.github.formatc00.mvp.base.BaseContract

interface CryptoListFragmentContract {
    
    interface View : BaseContract.View {
        
        fun showData(data: List<Cryptocurrency>)
    
        fun showPaginationProgress()
    
        fun hidePaginationProgress()
    }
    
    interface Presenter : BaseContract.Presenter<View> {
        
        var isLastPage: Boolean
        
        var isLoading: Boolean
        
        fun onLoadMoreItems()
    }
}
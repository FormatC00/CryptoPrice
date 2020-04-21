package com.github.formatc00.mvp.contract

import com.github.formatc00.mvp.base.BaseContract

interface HomeActivityContract {
    
    interface View : BaseContract.View
    
    interface Presenter : BaseContract.Presenter<View>
}
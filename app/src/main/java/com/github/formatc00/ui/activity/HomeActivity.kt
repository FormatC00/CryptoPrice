package com.github.formatc00.ui.activity

import com.github.formatc00.R
import com.github.formatc00.mvp.contract.HomeActivityContract

class HomeActivity : BaseActivity<HomeActivityContract.View, HomeActivityContract.Presenter>(),
                     HomeActivityContract.View {
    
    override val layoutId = R.layout.activity_home
}
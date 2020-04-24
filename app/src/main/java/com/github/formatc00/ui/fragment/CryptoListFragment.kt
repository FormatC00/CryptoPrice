package com.github.formatc00.ui.fragment

import com.github.formatc00.R
import com.github.formatc00.mvp.contract.CryptoListFragmentContract

class CryptoListFragment :
    BaseFragment<CryptoListFragmentContract.View, CryptoListFragmentContract.Presenter>(),
    CryptoListFragmentContract.View {
    
    override val layoutId = R.layout.fragment_list
}
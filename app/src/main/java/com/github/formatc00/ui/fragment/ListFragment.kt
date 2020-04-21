package com.github.formatc00.ui.fragment

import com.github.formatc00.R
import com.github.formatc00.mvp.contract.ListFragmentContract

class ListFragment : BaseFragment<ListFragmentContract.View, ListFragmentContract.Presenter>(),
                     ListFragmentContract.View {
    
    override val layoutId = R.layout.fragment_list
}
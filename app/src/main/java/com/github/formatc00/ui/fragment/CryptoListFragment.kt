package com.github.formatc00.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.formatc00.R
import com.github.formatc00.core.entity.Cryptocurrency
import com.github.formatc00.mvp.contract.CryptoListFragmentContract
import com.github.formatc00.ui.adapter.CryptoListAdapter
import com.github.formatc00.ui.adapter.listener.PaginationScrollListener
import com.github.formatc00.util.NumbersManager
import com.github.formatc00.util.UiUtils
import com.github.formatc00.util.extension.hide
import com.github.formatc00.util.extension.show
import kotlinx.android.synthetic.main.fragment_list.list
import kotlinx.android.synthetic.main.fragment_list.progressBar
import javax.inject.Inject

class CryptoListFragment :
    BaseFragment<CryptoListFragmentContract.View, CryptoListFragmentContract.Presenter>(),
    CryptoListFragmentContract.View {
    
    override val layoutId = R.layout.fragment_list
    
    @Inject
    lateinit var numbersManager: NumbersManager
    
    @Inject
    lateinit var uiUtils: UiUtils
    
    private lateinit var adapter: CryptoListAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CryptoListAdapter(numbersManager, uiUtils, presenter::onCryptoItemClick)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        list.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        list.layoutManager = manager
        list.adapter = adapter
        list.addOnScrollListener(object : PaginationScrollListener(manager) {
    
            override val isLastPage
                get() = presenter.isLastPage
    
            override val isLoading
                get() = presenter.isLoading
    
            override fun loadMoreItems() {
                presenter.onLoadMoreItems()
            }
        })
    }
    
    override fun showData(data: List<Cryptocurrency>) {
        adapter.addAll(data)
    }
    
    override fun showPaginationProgress() {
        adapter.showPaginationLoading()
    }
    
    override fun hidePaginationProgress() {
        adapter.hidePaginationLoading()
    }
    
    override fun showInitialProgress() {
        list.hide()
        progressBar.show()
    }
    
    override fun hideInitialProgress() {
        list.show()
        progressBar.hide()
    }
}
package com.github.formatc00.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.formatc00.R
import com.github.formatc00.core.entity.Cryptocurrency
import com.github.formatc00.mvp.contract.CryptoListFragmentContract
import com.github.formatc00.ui.adapter.CryptoListAdapter
import com.github.formatc00.util.NumbersManager
import kotlinx.android.synthetic.main.fragment_list.list
import javax.inject.Inject

class CryptoListFragment :
    BaseFragment<CryptoListFragmentContract.View, CryptoListFragmentContract.Presenter>(),
    CryptoListFragmentContract.View {
    
    override val layoutId = R.layout.fragment_list
    
    @Inject
    lateinit var numbersManager: NumbersManager
    
    private lateinit var adapter: CryptoListAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CryptoListAdapter(numbersManager)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        list.adapter = adapter
    }
    
    override fun showData(data: List<Cryptocurrency>) {
        adapter.replaceItems(data)
    }
}
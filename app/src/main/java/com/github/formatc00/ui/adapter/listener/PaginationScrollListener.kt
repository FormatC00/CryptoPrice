package com.github.formatc00.ui.adapter.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.formatc00.data.network.CRYPTOCURRENCIES_MAP_LIMIT

abstract class PaginationScrollListener(private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {
    
    abstract val isLastPage: Boolean
    
    abstract val isLoading: Boolean
    
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        if (!isLoading && !isLastPage) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= CRYPTOCURRENCIES_MAP_LIMIT
            ) {
                loadMoreItems()
            }
        }
    }
    
    protected abstract fun loadMoreItems()
}
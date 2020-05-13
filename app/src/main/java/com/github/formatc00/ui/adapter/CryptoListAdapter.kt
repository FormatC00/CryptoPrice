package com.github.formatc00.ui.adapter

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.github.formatc00.R
import com.github.formatc00.core.entity.Cryptocurrency
import com.github.formatc00.util.NumbersManager
import com.github.formatc00.util.UiUtils
import kotlinx.android.synthetic.main.item_cryptocurrency.view.capitalizationLabel
import kotlinx.android.synthetic.main.item_cryptocurrency.view.logo
import kotlinx.android.synthetic.main.item_cryptocurrency.view.nameLabel
import kotlinx.android.synthetic.main.item_cryptocurrency.view.numberLabel
import kotlinx.android.synthetic.main.item_cryptocurrency.view.priceChangeLabel
import kotlinx.android.synthetic.main.item_cryptocurrency.view.priceLabel
import kotlinx.android.synthetic.main.item_cryptocurrency.view.symbolLabel

class CryptoListAdapter(
    private val numbersManager: NumbersManager,
    private val uiUtils: UiUtils,
    private val onItemClick: (Cryptocurrency) -> Unit
) : BaseAdapter<Cryptocurrency>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            LOADING_VIEW_TYPE -> ProgressHolder(parent)
            DATA_VIEW_TYPE -> CryptoHolder(parent)
            else -> throw IllegalStateException("Unknown view type: $viewType")
        }
    
    override fun getItemViewType(position: Int) =
        if (items.getOrNull(position) == null) LOADING_VIEW_TYPE else DATA_VIEW_TYPE
    
    fun showPaginationLoading() {
        addItem(null)
    }
    
    fun hidePaginationLoading() {
        removeItemAt(itemCount - 1)
    }
    
    inner class ProgressHolder(
        parent: ViewGroup,
        @LayoutRes layoutRes: Int = R.layout.item_loading
    ) : BaseAdapter.BaseViewHolder<Cryptocurrency>(parent, layoutRes)
    
    inner class CryptoHolder(
        parent: ViewGroup,
        @LayoutRes layoutRes: Int = R.layout.item_cryptocurrency
    ) : BaseAdapter.BaseViewHolder<Cryptocurrency>(parent, layoutRes) {
        
        private val logo = itemView.logo
        
        private val numberLabel = itemView.numberLabel
        
        private val nameLabel = itemView.nameLabel
        
        private val symbolLabel = itemView.symbolLabel
    
        private val priceChangeLabel = itemView.priceChangeLabel
        
        private val priceLabel = itemView.priceLabel
        
        private val capitalizationLabel = itemView.capitalizationLabel
        
        override fun bind(item: Cryptocurrency, position: Int) {
            super.bind(item, position)
            
            val number = position + 1
            Glide.with(itemView.context).load(item.metadata?.logoUrl).into(logo)
            numberLabel.text = "$number"
            nameLabel.text = item.name
            symbolLabel.text = item.symbol
            item.quote?.apply {
                uiUtils.setupPriceChange(percentChange1h, priceChangeLabel)
                
                priceLabel.text = resources.getString(R.string.price_pattern, price)
                marketCapitalization?.apply {
                    val cap = numbersManager.formatBigNumber(this)
                    capitalizationLabel.text =
                        resources.getString(R.string.capitalization_pattern, cap)
                }
            }
    
            itemView.setOnClickListener { onItemClick(item) }
        }
    }
    
    companion object {
        
        const val LOADING_VIEW_TYPE = 10
        
        const val DATA_VIEW_TYPE = 20
    }
}
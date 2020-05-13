package com.github.formatc00.util

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.github.formatc00.R
import javax.inject.Inject

class UiUtils @Inject constructor() {
    
    fun setupPriceChange(priceChangeValue: Double?, textView: TextView) {
        priceChangeValue?.let {
            val sign = if (it > 0.0) "+" else ""
            val colorRes = if (it >= 0.0)
                R.color.price_up_color
            else
                R.color.price_down_color
            val context = textView.context
            textView.setTextColor(ContextCompat.getColor(context, colorRes))
            textView.text = context.getString(R.string.price_difference_pattern, sign, it)
        }
    }
}
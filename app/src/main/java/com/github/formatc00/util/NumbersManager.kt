package com.github.formatc00.util

import android.content.res.Resources
import androidx.annotation.StringRes
import com.github.formatc00.R
import com.github.formatc00.core.entity.Numbers
import com.github.formatc00.util.extension.floor2Digits
import javax.inject.Inject

class NumbersManager @Inject constructor(private val resources: Resources) {
    
    /**
     * Formats big number to easy-to-read string
     * e.g 1_234_567.98 -> 1.23 M
     * 12_345_678.98 -> 12.34 M
     * 143_542_345_678.98 -> 143.542 Bn
     */
    fun formatBigNumber(num: Double): String {
        val numLong = num.toLong()
        val thousand = Numbers.THOUSAND.longValue
        val million = Numbers.MILLION.longValue
        val billion = Numbers.BILLION.longValue
        val trillion = Numbers.TRILLION.longValue
        val quadrillion = Numbers.QUADRILLION.longValue
        
        return when {
            (numLong in thousand until million) ->
                getPatternString(R.string.thousand_pattern, num / thousand)
            
            (numLong in million until billion) ->
                getPatternString(R.string.million_pattern, num / million)
            
            (numLong in billion until trillion) ->
                getPatternString(R.string.billion_pattern, num / billion)
            
            (numLong in trillion until quadrillion) ->
                getPatternString(R.string.trillion_pattern, num / trillion)
            
            else -> num.floor2Digits().toString()
        }
    }
    
    private fun getPatternString(@StringRes res: Int, num: Double) =
        resources.getString(res, num.floor2Digits())
}
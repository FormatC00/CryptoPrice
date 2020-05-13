package com.github.formatc00.ui.navigation

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.github.formatc00.core.entity.Cryptocurrency
import com.github.formatc00.ui.activity.CryptoDetailsActivity
import com.github.formatc00.ui.fragment.CryptoListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    
    class CryptoListScreen : SupportAppScreen() {
        
        override fun getFragment(): Fragment {
            return CryptoListFragment()
        }
    }
    
    class CryptoDetailsScreen(private val cryptocurrency: Cryptocurrency) : SupportAppScreen() {
        
        override fun getActivityIntent(context: Context) =
            Intent(context, CryptoDetailsActivity::class.java).apply {
                putExtra(CRYPTOCURRENCY_EXTRA, cryptocurrency)
            }
        
        companion object {
            
            const val CRYPTOCURRENCY_EXTRA = "CRYPTOCURRENCY_EXTRA"
        }
    }
}
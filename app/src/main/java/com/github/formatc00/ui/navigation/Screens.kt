package com.github.formatc00.ui.navigation

import androidx.fragment.app.Fragment
import com.github.formatc00.ui.fragment.CryptoListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    
    class CryptoListScreen : SupportAppScreen() {
        
        override fun getFragment(): Fragment {
            return CryptoListFragment()
        }
    }
}
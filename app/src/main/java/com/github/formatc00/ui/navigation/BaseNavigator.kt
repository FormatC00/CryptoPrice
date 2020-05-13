package com.github.formatc00.ui.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.github.formatc00.R
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

open class BaseNavigator internal constructor(
    private val fragmentActivity: FragmentActivity
) : SupportAppNavigator(fragmentActivity, containerId) {
    
    override fun setupFragmentTransaction(
        command: Command,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        fragmentTransaction: FragmentTransaction
    ) {
        fragmentTransaction.setCustomAnimations(
            android.R.anim.fade_in, android.R.anim.fade_out,
            android.R.anim.fade_in, android.R.anim.fade_out
        )
    }
    
    companion object {
        
        @IdRes
        private val containerId = R.id.fragment_container
    }
}
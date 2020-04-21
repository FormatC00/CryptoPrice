package com.github.formatc00.mvp.base

import androidx.annotation.StringRes

interface MessageInterface {
    
    fun showMessage(@StringRes titleResId: Int,
                    @StringRes messageResId: Int,
                    confirmationListener: () -> Unit = {})
    
    fun showError(@StringRes messageResId: Int, confirmationListener: () -> Unit = {})
    
    fun showError(throwable: Throwable)
    
    fun showInfo(@StringRes messageResId: Int, confirmationListener: () -> Unit = {})
}
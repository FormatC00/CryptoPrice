package com.github.formatc00

import com.github.formatc00.di.component.DaggerAppComponent
import dagger.android.DaggerApplication

class CryptoPriceApplication : DaggerApplication() {
    
    override fun applicationInjector() = DaggerAppComponent.builder().application(this).build()
}
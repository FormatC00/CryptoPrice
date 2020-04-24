package com.github.formatc00.di.module.activity

import com.github.formatc00.di.module.fragment.CryptoListFragmentModule
import com.github.formatc00.di.scope.FragmentScope
import com.github.formatc00.ui.fragment.CryptoListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface HomeActivityFragmentProvider {
    
    @FragmentScope
    @ContributesAndroidInjector(modules = [CryptoListFragmentModule::class])
    fun contributesCryptoListFragment(): CryptoListFragment
}
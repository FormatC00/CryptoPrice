package com.github.formatc00.di.module.activity

import com.github.formatc00.di.module.fragment.ListFragmentModule
import com.github.formatc00.di.scope.FragmentScope
import com.github.formatc00.ui.fragment.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface HomeActivityFragmentProvider {
    
    @FragmentScope
    @ContributesAndroidInjector(modules = [ListFragmentModule::class])
    fun contributesListFragment(): ListFragment
}
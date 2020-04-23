package com.github.formatc00.di.module

import com.github.formatc00.core.facade.CryptoFacade
import com.github.formatc00.data.facade.CryptoFacadeImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class FacadeModule {
    
    @Binds
    @Singleton
    abstract fun provideCryptoRepositoryFacade(cryptoFacade: CryptoFacadeImpl): CryptoFacade
}
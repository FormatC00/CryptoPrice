package com.github.formatc00.di.module

import com.github.formatc00.core.repository.CryptoRepository
import com.github.formatc00.data.repository.CryptoRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    
    @Binds
    @Singleton
    abstract fun provideCryptoRepository(cryptoRepository: CryptoRepositoryImpl): CryptoRepository
}
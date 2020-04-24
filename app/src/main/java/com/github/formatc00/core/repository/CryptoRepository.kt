package com.github.formatc00.core.repository

import com.github.formatc00.core.entity.Cryptocurrency
import com.github.formatc00.core.entity.CryptocurrencyMetadata
import io.reactivex.Single

interface CryptoRepository {
    
    fun getCryptocurrencyMap(): Single<List<Cryptocurrency>>
    
    fun getCryptocurrenciesMetadata(ids: String): Single<List<CryptocurrencyMetadata>>
}
package com.github.formatc00.core.repository

import com.github.formatc00.core.entity.Cryptocurrency
import com.github.formatc00.core.entity.CryptocurrencyMetadata
import com.github.formatc00.core.entity.CryptocurrencyQuote
import io.reactivex.Single

interface CryptoRepository {
    
    fun getMap(offset: Int): Single<List<Cryptocurrency>>
    
    fun getMetadata(ids: String): Single<List<CryptocurrencyMetadata>>
    
    fun getQuote(ids: String): Single<List<CryptocurrencyQuote>>
}
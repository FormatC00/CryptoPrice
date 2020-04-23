package com.github.formatc00.core.repository

import com.github.formatc00.core.entity.Cryptocurrency
import io.reactivex.Single

interface CryptoRepository {
    
    fun getCryptocurrencyMap(): Single<List<Cryptocurrency>>
}
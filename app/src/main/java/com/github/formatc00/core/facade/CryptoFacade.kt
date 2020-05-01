package com.github.formatc00.core.facade

import com.github.formatc00.core.entity.Cryptocurrency
import io.reactivex.Single

interface CryptoFacade {
    
    fun getCryptocurrenciesMap(offset: Int): Single<List<Cryptocurrency>>
}
package com.github.formatc00.data.facade

import com.github.formatc00.core.facade.CryptoFacade
import com.github.formatc00.core.repository.CryptoRepository
import javax.inject.Inject

class CryptoFacadeImpl @Inject constructor(
    private val repository: CryptoRepository
) : CryptoFacade {
    
    override fun getCryptocurrenciesMap() = repository.getCryptocurrencyMap()
}
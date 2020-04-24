package com.github.formatc00.data.facade

import com.github.formatc00.core.entity.Cryptocurrency
import com.github.formatc00.core.entity.CryptocurrencyMetadata
import com.github.formatc00.core.facade.CryptoFacade
import com.github.formatc00.core.repository.CryptoRepository
import javax.inject.Inject

class CryptoFacadeImpl @Inject constructor(
    private val repository: CryptoRepository
) : CryptoFacade {
    
    override fun getCryptocurrenciesMap() = repository.getCryptocurrencyMap()
        .flatMap { cryptocurrencies ->
            repository.getCryptocurrenciesMetadata(getStringIds(cryptocurrencies)).map {
                getCryptocurrenciesWithMetadata(cryptocurrencies, it)
            }
        }
    
    private fun getCryptocurrenciesWithMetadata(
        cryptocurrencies: List<Cryptocurrency>,
        metadata: List<CryptocurrencyMetadata>
    ): List<Cryptocurrency> = cryptocurrencies.map { cryptocurrency ->
        val foundMetadata = metadata.find { cryptocurrency.id == it.id }
        cryptocurrency.metadata = foundMetadata
        cryptocurrency
    }
    
    private fun getStringIds(cryptocurrenciesList: List<Cryptocurrency>) =
        cryptocurrenciesList.map { it.id }.joinToString(",")
}
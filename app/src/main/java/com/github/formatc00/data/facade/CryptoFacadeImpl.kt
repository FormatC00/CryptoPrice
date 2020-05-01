package com.github.formatc00.data.facade

import com.github.formatc00.core.entity.Cryptocurrency
import com.github.formatc00.core.entity.CryptocurrencyMetadata
import com.github.formatc00.core.entity.CryptocurrencyQuote
import com.github.formatc00.core.facade.CryptoFacade
import com.github.formatc00.core.repository.CryptoRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class CryptoFacadeImpl @Inject constructor(
    private val repository: CryptoRepository
) : CryptoFacade {
    
    override fun getCryptocurrenciesMap(offset: Int) =
        repository.getMap(offset).flatMap(::getExtendedMapSingle)
    
    private fun getExtendedMapSingle(cryptocurrencies: List<Cryptocurrency>): Single<List<Cryptocurrency>> {
        val ids = formatStringIds(cryptocurrencies)
        return Single.zip(
            repository.getMetadata(ids),
            repository.getQuote(ids),
            mergeNewCryptocurrencyData(cryptocurrencies)
        )
    }
    
    private fun mergeNewCryptocurrencyData(cryptocurrencies: List<Cryptocurrency>):
            BiFunction<List<CryptocurrencyMetadata>, List<CryptocurrencyQuote>, List<Cryptocurrency>> {
        return BiFunction { metadatas: List<CryptocurrencyMetadata>, quotes: List<CryptocurrencyQuote> ->
            cryptocurrencies.onEach { cryptocurrency ->
                val foundQuote = quotes.find { cryptocurrency.id == it.id }
                val foundMetadata = metadatas.find { cryptocurrency.id == it.id }
                cryptocurrency.metadata = foundMetadata
                cryptocurrency.quote = foundQuote
            }
        }
    }
    
    private fun formatStringIds(cryptocurrenciesList: List<Cryptocurrency>) =
        cryptocurrenciesList.map { it.id }.joinToString(",")
}
package com.github.formatc00.data.network.mapper

import com.github.formatc00.core.entity.CryptocurrencyQuote
import com.github.formatc00.data.network.entity.CryptocurrencyQuoteDescriptionNetwork
import com.github.formatc00.data.network.entity.CryptocurrencyQuoteResponse
import javax.inject.Inject

private const val USD_SYMBOL = "USD"

class CryptocurrencyQuoteMapper @Inject constructor() {
    
    fun map(response: CryptocurrencyQuoteResponse): List<CryptocurrencyQuote> {
        val data = response.data ?: return listOf()
        return data.toList().map { mapToQuote(it.second) }
    }
    
    fun mapToQuote(networkItem: CryptocurrencyQuoteDescriptionNetwork): CryptocurrencyQuote {
        val quote = networkItem.run {
            CryptocurrencyQuote(id, maxSupply, totalSupply)
        }
        networkItem.quote?.get(USD_SYMBOL)?.apply {
            quote.price = price
            quote.volume24h = volume24h
            quote.percentChange1h = percentChange1h
            quote.percentChange24h = percentChange24h
            quote.percentChange7d = percentChange7d
            quote.marketCapitalization = marketCapitalization
        }
        
        return quote
    }
}
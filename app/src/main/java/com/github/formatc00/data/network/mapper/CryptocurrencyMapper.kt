package com.github.formatc00.data.network.mapper

import com.github.formatc00.core.entity.Cryptocurrency
import com.github.formatc00.data.network.entity.CryptocurrencyMapResponse
import com.github.formatc00.data.network.entity.CryptocurrencyNetwork
import javax.inject.Inject

class CryptocurrencyMapper @Inject constructor() {
    
    fun map(response: CryptocurrencyMapResponse): List<Cryptocurrency> {
        val data = response.data ?: return listOf()
        return data.map(::map)
    }
    
    fun map(cryptocurrencyNetwork: CryptocurrencyNetwork) = cryptocurrencyNetwork.run {
        Cryptocurrency(id, name, symbol, rank)
    }
}
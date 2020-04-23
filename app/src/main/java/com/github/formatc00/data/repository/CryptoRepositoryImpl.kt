package com.github.formatc00.data.repository

import com.github.formatc00.core.repository.CryptoRepository
import com.github.formatc00.data.network.CryptoApi
import com.github.formatc00.data.network.mapper.CryptocurrencyMapper
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val api: CryptoApi,
    private val cryptocurrencyMapper: CryptocurrencyMapper
) : CryptoRepository {
    
    override fun getCryptocurrencyMap() = api.getCryptocurrencyMap().map(cryptocurrencyMapper::map)
}
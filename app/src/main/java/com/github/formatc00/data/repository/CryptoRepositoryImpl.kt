package com.github.formatc00.data.repository

import com.github.formatc00.core.repository.CryptoRepository
import com.github.formatc00.data.network.CryptoApi
import com.github.formatc00.data.network.mapper.CryptocurrencyMapper
import com.github.formatc00.data.network.mapper.CryptocurrencyMetadataMapper
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val api: CryptoApi,
    private val cryptocurrencyMapper: CryptocurrencyMapper,
    private val cryptocurrencyMetadataMapper: CryptocurrencyMetadataMapper
) : CryptoRepository {
    
    override fun getCryptocurrencyMap() = api.getCryptocurrencyMap()
        .map(cryptocurrencyMapper::map)
    
    override fun getCryptocurrenciesMetadata(ids: String) = api.getCoinMetaData(ids)
        .map(cryptocurrencyMetadataMapper::map)
}
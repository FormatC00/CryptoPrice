package com.github.formatc00.data.repository

import com.github.formatc00.core.repository.CryptoRepository
import com.github.formatc00.data.network.CryptoApi
import com.github.formatc00.data.network.mapper.CryptocurrencyMapper
import com.github.formatc00.data.network.mapper.CryptocurrencyMetadataMapper
import com.github.formatc00.data.network.mapper.CryptocurrencyQuoteMapper
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val api: CryptoApi,
    private val cryptocurrencyMapper: CryptocurrencyMapper,
    private val cryptocurrencyMetadataMapper: CryptocurrencyMetadataMapper,
    private val cryptocurrencyQuoteMapper: CryptocurrencyQuoteMapper
) : CryptoRepository {
    
    override fun getMap() = api.getMap().map(cryptocurrencyMapper::map)
    
    override fun getMetadata(ids: String) = api.getMetadata(ids)
        .map(cryptocurrencyMetadataMapper::map)
    
    override fun getQuote(ids: String) = api.getQuote(ids).map(cryptocurrencyQuoteMapper::map)
}
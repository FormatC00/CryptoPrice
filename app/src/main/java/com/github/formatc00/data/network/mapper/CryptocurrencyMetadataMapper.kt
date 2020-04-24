package com.github.formatc00.data.network.mapper

import com.github.formatc00.core.entity.CryptocurrencyMetadata
import com.github.formatc00.data.network.entity.CryptocurrencyMetadataNetwork
import com.github.formatc00.data.network.entity.CryptocurrencyMetadataResponse
import javax.inject.Inject

class CryptocurrencyMetadataMapper @Inject constructor(
    private val cryptocurrencyUrlsMapper: CryptocurrencyUrlsMapper
) {
    
    fun map(response: CryptocurrencyMetadataResponse): List<CryptocurrencyMetadata> {
        val data = response.data ?: return listOf()
        return data.toList().map { it.second }.map(this::map)
    }
    
    fun map(metadataNetwork: CryptocurrencyMetadataNetwork) = metadataNetwork.run {
        CryptocurrencyMetadata(id, description, logoUrl, cryptocurrencyUrlsMapper.map(urls))
    }
}
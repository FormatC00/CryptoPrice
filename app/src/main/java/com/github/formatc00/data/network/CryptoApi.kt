package com.github.formatc00.data.network

import com.github.formatc00.data.network.entity.CryptocurrencyMapResponse
import com.github.formatc00.data.network.entity.CryptocurrencyMetadataResponse
import com.github.formatc00.data.network.entity.CryptocurrencyQuoteResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

const val CRYPTOCURRENCIES_MAP_LIMIT = 100

const val CRYPTOCURRENCIES_MAP_DEFAULT_OFFSET = 1

interface CryptoApi {
    
    @GET("/v1/cryptocurrency/map?sort=cmc_rank&limit=$CRYPTOCURRENCIES_MAP_LIMIT")
    fun getMap(@Query("start") offset: Int = CRYPTOCURRENCIES_MAP_DEFAULT_OFFSET): Single<CryptocurrencyMapResponse>
    
    @GET("/v1/cryptocurrency/info")
    fun getMetadata(@Query("id") ids: String): Single<CryptocurrencyMetadataResponse>
    
    @GET("/v1/cryptocurrency/quotes/latest")
    fun getQuote(@Query("id") ids: String): Single<CryptocurrencyQuoteResponse>
}
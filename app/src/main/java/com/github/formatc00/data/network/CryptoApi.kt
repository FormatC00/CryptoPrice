package com.github.formatc00.data.network

import com.github.formatc00.data.network.entity.CryptocurrencyMapResponse
import com.github.formatc00.data.network.entity.CryptocurrencyMetadataResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {
    
    @GET("/v1/cryptocurrency/map?sort=cmc_rank&limit=100")
    fun getCryptocurrencyMap(): Single<CryptocurrencyMapResponse>
    
    @GET("/v1/cryptocurrency/info")
    fun getCoinMetaData(@Query("id") ids: String): Single<CryptocurrencyMetadataResponse>
}
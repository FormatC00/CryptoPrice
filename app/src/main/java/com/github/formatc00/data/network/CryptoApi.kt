package com.github.formatc00.data.network

import com.github.formatc00.data.network.entity.CryptocurrencyMapResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CryptoApi {
    
    @GET("/v1/cryptocurrency/map?limit=100&sort=cmc_rank")
    fun getCryptocurrencyMap(): Single<CryptocurrencyMapResponse>
}
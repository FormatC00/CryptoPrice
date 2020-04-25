package com.github.formatc00.data.network.entity

import com.google.gson.annotations.SerializedName

data class CryptocurrencyQuoteDescriptionNetwork(
    @SerializedName("id") val id: Long?,
    @SerializedName("max_supply") val maxSupply: Double?,
    @SerializedName("total_supply") val totalSupply: Double?,
    @SerializedName("quote") val quote: Map<String, CryptocurrencyQuoteNetwork>?
)
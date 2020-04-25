package com.github.formatc00.data.network.entity

import com.google.gson.annotations.SerializedName

data class CryptocurrencyQuoteNetwork(
    @SerializedName("price") val price: Double?,
    @SerializedName("volume_24h") val volume24h: Double?,
    @SerializedName("percent_change_1h") val percentChange1h: Double?,
    @SerializedName("percent_change_24h") val percentChange24h: Double?,
    @SerializedName("percent_change_7d") val percentChange7d: Double?,
    @SerializedName("market_cap") val marketCapitalization: Double?
)
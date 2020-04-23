package com.github.formatc00.data.network.entity

import com.google.gson.annotations.SerializedName

data class CryptocurrencyNetwork(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("symbol") val symbol: String? = null,
    @SerializedName("rank") val rank: Int? = null
)
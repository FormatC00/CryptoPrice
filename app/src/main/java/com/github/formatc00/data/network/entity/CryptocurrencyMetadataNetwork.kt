package com.github.formatc00.data.network.entity

import com.google.gson.annotations.SerializedName

data class CryptocurrencyMetadataNetwork(
    @SerializedName("id") val id: Long?,
    @SerializedName("description") val description: String?,
    @SerializedName("logo") val logoUrl: String?,
    @SerializedName("urls") val urls: CryptocurrencyUrlsNetwork?
)
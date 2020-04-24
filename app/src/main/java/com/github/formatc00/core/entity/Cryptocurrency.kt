package com.github.formatc00.core.entity

data class Cryptocurrency(
    val id: Long?,
    val name: String?,
    val symbol: String?,
    val rank: Int?,
    var metadata: CryptocurrencyMetadata? = null
)
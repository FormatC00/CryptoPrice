package com.github.formatc00.core.entity

data class Cryptocurrency(
    val id: Long?,
    val name: String? = null,
    val symbol: String? = null,
    val rank: Int? = null,
    var metadata: CryptocurrencyMetadata? = null,
    var quote: CryptocurrencyQuote? = null
)
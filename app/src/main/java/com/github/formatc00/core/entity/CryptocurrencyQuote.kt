package com.github.formatc00.core.entity

data class CryptocurrencyQuote(
    val id: Long?,
    val maxSupply: Double? = null,
    val totalSupply: Double? = null,
    var price: Double? = null,
    var volume24h: Double? = null,
    var percentChange1h: Double? = null,
    var percentChange24h: Double? = null,
    var percentChange7d: Double? = null,
    var marketCapitalization: Double? = null
)
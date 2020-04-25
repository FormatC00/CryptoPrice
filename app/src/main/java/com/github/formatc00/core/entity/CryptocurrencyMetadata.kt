package com.github.formatc00.core.entity

data class CryptocurrencyMetadata(
    val id: Long?,
    val description: String? = null,
    val logoUrl: String? = null,
    val urls: CryptocurrencyUrls? = null
)
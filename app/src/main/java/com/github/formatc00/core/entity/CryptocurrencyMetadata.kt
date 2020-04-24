package com.github.formatc00.core.entity

data class CryptocurrencyMetadata(
    val id: Long?,
    val description: String?,
    val logoUrl: String?,
    val urls: CryptocurrencyUrls?
)
package com.github.formatc00.core.entity

enum class Numbers(val longValue: Long) {
    
    THOUSAND(1_000),
    MILLION(1_000_000),
    BILLION(1_000_000_000),
    TRILLION(1_000_000_000_000),
    QUADRILLION(1_000_000_000_000_000)
}
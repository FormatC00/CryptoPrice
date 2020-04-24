package com.github.formatc00.data.network.mapper

import com.github.formatc00.core.entity.CryptocurrencyUrls
import com.github.formatc00.data.network.entity.CryptocurrencyUrlsNetwork
import javax.inject.Inject

class CryptocurrencyUrlsMapper @Inject constructor() {
    
    fun map(cryptocurrencyUrlsNetwork: CryptocurrencyUrlsNetwork?): CryptocurrencyUrls? =
        cryptocurrencyUrlsNetwork?.run {
            CryptocurrencyUrls(
                website,
                technicalDoc,
                twitter,
                reddit,
                messageBoard,
                announcement,
                chat,
                explorer,
                sourceCode
            )
        }
}
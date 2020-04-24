package com.github.formatc00.data.network.entity

import com.google.gson.annotations.SerializedName

data class CryptocurrencyUrlsNetwork(
    @SerializedName("website") val website: List<String>,
    @SerializedName("technical_doc") val technicalDoc: List<String>,
    @SerializedName("twitter") val twitter: List<String>,
    @SerializedName("reddit") val reddit: List<String>,
    @SerializedName("message_board") val messageBoard: List<String>,
    @SerializedName("announcement") val announcement: List<String>,
    @SerializedName("chat") val chat: List<String>,
    @SerializedName("explorer") val explorer: List<String>,
    @SerializedName("source_code") val sourceCode: List<String>
)
package com.github.formatc00.data.network.entity

import com.google.gson.annotations.SerializedName

data class StatusResponse(
    @SerializedName("error_code") val errorCode: Int? = null,
    @SerializedName("error_message") val errorMessage: String? = null
)
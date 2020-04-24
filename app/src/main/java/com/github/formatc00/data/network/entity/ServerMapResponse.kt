package com.github.formatc00.data.network.entity

import com.google.gson.annotations.SerializedName

open class ServerMapResponse<T> {
    
    @SerializedName("status")
    var status: StatusResponse? = null
    
    @SerializedName("data")
    var data: Map<String, T>? = null
}
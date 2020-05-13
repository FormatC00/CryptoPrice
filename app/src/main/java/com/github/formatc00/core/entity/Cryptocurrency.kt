package com.github.formatc00.core.entity

import android.os.Parcel
import android.os.Parcelable

data class Cryptocurrency(
    val id: Long? = null,
    val name: String? = null,
    val symbol: String? = null,
    val rank: Int? = null,
    var metadata: CryptocurrencyMetadata? = null,
    var quote: CryptocurrencyQuote? = null
) : Parcelable {
    
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readParcelable(CryptocurrencyMetadata::class.java.classLoader),
        parcel.readParcelable(CryptocurrencyQuote::class.java.classLoader)
    )
    
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(symbol)
        parcel.writeValue(rank)
        parcel.writeParcelable(metadata, flags)
        parcel.writeParcelable(quote, flags)
    }
    
    override fun describeContents(): Int {
        return 0
    }
    
    companion object CREATOR : Parcelable.Creator<Cryptocurrency> {
        override fun createFromParcel(parcel: Parcel): Cryptocurrency {
            return Cryptocurrency(parcel)
        }
        
        override fun newArray(size: Int): Array<Cryptocurrency?> {
            return arrayOfNulls(size)
        }
    }
}
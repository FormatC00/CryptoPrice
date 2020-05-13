package com.github.formatc00.core.entity

import android.os.Parcel
import android.os.Parcelable

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
) : Parcelable {
    
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double
    )
    
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeValue(maxSupply)
        parcel.writeValue(totalSupply)
        parcel.writeValue(price)
        parcel.writeValue(volume24h)
        parcel.writeValue(percentChange1h)
        parcel.writeValue(percentChange24h)
        parcel.writeValue(percentChange7d)
        parcel.writeValue(marketCapitalization)
    }
    
    override fun describeContents(): Int {
        return 0
    }
    
    companion object CREATOR : Parcelable.Creator<CryptocurrencyQuote> {
        override fun createFromParcel(parcel: Parcel): CryptocurrencyQuote {
            return CryptocurrencyQuote(parcel)
        }
        
        override fun newArray(size: Int): Array<CryptocurrencyQuote?> {
            return arrayOfNulls(size)
        }
    }
}
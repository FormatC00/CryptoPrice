package com.github.formatc00.core.entity

import android.os.Parcel
import android.os.Parcelable

data class CryptocurrencyMetadata(
    val id: Long?,
    val description: String? = null,
    val logoUrl: String? = null,
    val urls: CryptocurrencyUrls? = null
) : Parcelable {
    
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(CryptocurrencyUrls::class.java.classLoader)
    )
    
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(description)
        parcel.writeString(logoUrl)
        parcel.writeParcelable(urls, flags)
    }
    
    override fun describeContents(): Int {
        return 0
    }
    
    companion object CREATOR : Parcelable.Creator<CryptocurrencyMetadata> {
        override fun createFromParcel(parcel: Parcel): CryptocurrencyMetadata {
            return CryptocurrencyMetadata(parcel)
        }
        
        override fun newArray(size: Int): Array<CryptocurrencyMetadata?> {
            return arrayOfNulls(size)
        }
    }
}
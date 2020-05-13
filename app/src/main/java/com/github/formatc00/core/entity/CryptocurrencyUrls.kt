package com.github.formatc00.core.entity

import android.os.Parcel
import android.os.Parcelable

data class CryptocurrencyUrls(
    val website: List<String>,
    val technicalDoc: List<String>,
    val twitter: List<String>,
    val reddit: List<String>,
    val messageBoard: List<String>,
    val announcement: List<String>,
    val chat: List<String>,
    val explorer: List<String>,
    val sourceCode: List<String>
) : Parcelable {
    
    constructor(parcel: Parcel) : this(
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList()
    )
    
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(website)
        parcel.writeStringList(technicalDoc)
        parcel.writeStringList(twitter)
        parcel.writeStringList(reddit)
        parcel.writeStringList(messageBoard)
        parcel.writeStringList(announcement)
        parcel.writeStringList(chat)
        parcel.writeStringList(explorer)
        parcel.writeStringList(sourceCode)
    }
    
    override fun describeContents(): Int {
        return 0
    }
    
    companion object CREATOR : Parcelable.Creator<CryptocurrencyUrls> {
        override fun createFromParcel(parcel: Parcel): CryptocurrencyUrls {
            return CryptocurrencyUrls(parcel)
        }
        
        override fun newArray(size: Int): Array<CryptocurrencyUrls?> {
            return arrayOfNulls(size)
        }
    }
}
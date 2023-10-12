package com.android.applemarket

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.TypeParceler

@Parcelize
data class MyItem(val Image: Int, val Id: String, val Description: String, val Solder: String, val Price: Int, val Address: String, var Interest: Int, val Chatting: Int, var heart: Boolean) : Parcelable
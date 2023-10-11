package com.android.applemarket

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.TypeParceler

@Parcelize
data class MyItem(
    val Image: Int,
    val ItemTitle: String,
    val ItemDetail: String,
    val SellerName: String,
    val Price: Int,
    val Address: String,
    var InterestCnt: Int,
    val ChatCnt: Int,
    var isLike: Boolean
) : Parcelable
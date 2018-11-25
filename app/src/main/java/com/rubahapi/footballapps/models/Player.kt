package com.rubahapi.footballapps.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
    @SerializedName("idPlayer")
    val idPlayer:String? = null,

    @SerializedName("strPlayer")
    val strPlayer:String? = null,

    @SerializedName("strCutout")
    val strCutout:String? = null,

    @SerializedName("strPosition")
    val strPosition:String? = null,

    @SerializedName("strThumb")
    val strThumb:String? = null,

    @SerializedName("strWeight")
    val strWeight:String? = null,

    @SerializedName("strHeight")
    val strHeight:String? = null,

    @SerializedName("strDescriptionEN")
    val strDescriptionEN:String? = null
): Parcelable
package com.rubahapi.footballapps.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
        @SerializedName("idTeam")
        var teamId: String? = null,

        @SerializedName("strTeam")
        var teamName: String? = null,

        @SerializedName("strTeamBadge")
        var teamBadge: String? = null,

        @SerializedName("intFormedYear")
        var intFormedYear:Int? = null,

        @SerializedName("strStadium")
        var strStadium:String? = null,

        @SerializedName("strDescriptionEN")
        var strDescriptionEN:String? = null

        ):Parcelable
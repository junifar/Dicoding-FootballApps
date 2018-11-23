package com.rubahapi.footballapps.models

import com.google.gson.annotations.SerializedName

/**
 * Created by root on 1/23/18.
 */
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

        )
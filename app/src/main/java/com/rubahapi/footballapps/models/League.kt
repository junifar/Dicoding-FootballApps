package com.rubahapi.footballapps.models

import com.google.gson.annotations.SerializedName

data class League(

    @SerializedName("idLeague")
    var leagueId: Int? = 0,

    @SerializedName("strLeague")
    var leagueName:String? = null

){
    override fun toString(): String {
        return leagueName.toString()
    }
}
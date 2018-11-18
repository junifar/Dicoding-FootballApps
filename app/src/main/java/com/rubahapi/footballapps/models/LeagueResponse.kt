package com.rubahapi.footballapps.models

import com.google.gson.annotations.SerializedName
import com.rubahapi.footballapps.models.League

data class LeagueResponse(
    @SerializedName("leagues")
    val leagues: List<League>
)
package com.rubahapi.footballapps.models

import com.google.gson.annotations.SerializedName

data class MatchResponse(
    @SerializedName("events")
    val nextMatches: List<Match>
)
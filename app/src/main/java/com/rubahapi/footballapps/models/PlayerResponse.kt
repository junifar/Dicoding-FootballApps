package com.rubahapi.footballapps.models

import com.google.gson.annotations.SerializedName

data class PlayerResponse(
        @SerializedName("player")
        val players: List<Player>)
package com.rubahapi.footballapps.favoritedetail.favoritedetailmatch

import com.rubahapi.footballapps.models.Team

interface FavoriteDetailMatchView{
    fun showHomeFlag(data: List<Team>)
    fun showAwayFlag(data: List<Team>)
}
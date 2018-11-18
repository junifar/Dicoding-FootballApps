package com.rubahapi.footballapps.home.fragment.match.nextmatch

import com.rubahapi.footballapps.models.LeagueResponse

interface NextMatchView{
    fun showLoading()
    fun hideLoading()
    fun showLeagueList(data: LeagueResponse)
}
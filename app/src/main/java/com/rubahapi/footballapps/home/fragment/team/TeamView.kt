package com.rubahapi.footballapps.home.fragment.team

import com.rubahapi.footballapps.models.LeagueResponse
import com.rubahapi.footballapps.models.Team

interface TeamView{
    fun showLoading()
    fun hideLoading()
    fun showLeagueList(data: LeagueResponse)
    fun showTeamLeague(data: List<Team>)
}
package com.rubahapi.footballapps.home.fragment.match.pastmatch

import com.rubahapi.footballapps.models.LeagueResponse
import com.rubahapi.footballapps.models.Match

interface PastMatchView{
    fun showLoading()
    fun hideLoading()
    fun showLeagueList(data: LeagueResponse)
    fun showPastMatch(data: List<Match>)
}
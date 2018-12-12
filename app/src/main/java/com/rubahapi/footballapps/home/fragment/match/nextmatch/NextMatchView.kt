package com.rubahapi.footballapps.home.fragment.match.nextmatch

import com.rubahapi.footballapps.models.LeagueResponse
import com.rubahapi.footballapps.models.Match

interface NextMatchView{
    fun showLoading()
    fun hideLoading()
    fun showLeagueList(data: LeagueResponse)
    fun showNextMatch(data: List<Match>)
    fun showBlankMatch()
    fun onAttachView()
    fun onDetachView()
}
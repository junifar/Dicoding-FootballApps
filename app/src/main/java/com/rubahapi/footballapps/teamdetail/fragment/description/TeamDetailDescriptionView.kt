package com.rubahapi.footballapps.teamdetail.fragment.description

import com.rubahapi.footballapps.models.Team

interface TeamDetailDescriptionView{
    fun showLoading()
    fun hideLoading()
    fun showTeamLeague(data: List<Team>)
}
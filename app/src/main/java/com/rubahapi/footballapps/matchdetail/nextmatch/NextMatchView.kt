package com.rubahapi.footballapps.matchdetail.nextmatch

import com.rubahapi.footballapps.models.Team

interface NextMatchView{
    fun showHomeFlag(data: List<Team>)
    fun showAwayFlag(data: List<Team>)
}
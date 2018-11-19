package com.rubahapi.footballapps.matchdetail.pastmatch

import com.rubahapi.footballapps.models.Team

interface PastMatchView{
    fun showHomeFlag(data: List<Team>)
    fun showAwayFlag(data: List<Team>)
}
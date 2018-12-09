package com.rubahapi.footballapps.teamdetail.fragment.squad

import com.rubahapi.footballapps.models.Player

interface TeamDetailSquadView{
    fun showLoading()
    fun hideLoading()
    fun showPlayer(data: List<Player>)
}
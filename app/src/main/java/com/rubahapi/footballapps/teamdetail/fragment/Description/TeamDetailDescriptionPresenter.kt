package com.rubahapi.footballapps.teamdetail.fragment.Description

import com.rubahapi.footballapps.models.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailDescriptionPresenter(private val view: TeamDetailDescriptionFragment){

    fun getTeamLeague(description:String){
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main){
//            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamLeague(name)).await(),
//                TeamResponse::class.java)
            val data:MutableList<Team> = mutableListOf()
            data.add(Team("1", "", "", 0, "", description))
            view.showTeamLeague(data)
            view.hideLoading()
        }
    }
}
package com.rubahapi.footballapps.matchdetail.pastmatch

import com.google.gson.Gson
import com.rubahapi.footballapps.api.ApiRepository
import com.rubahapi.footballapps.api.TheSportDBApi
import com.rubahapi.footballapps.models.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PastMatchPresenter(private val view: PastMatchDetailActivity,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson
){
    fun getHomeFlag(teamID: Int){
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamFlag(teamID.toString())).await(),
                TeamResponse::class.java)

            view.showHomeFlag(data.teams)
        }
    }

    fun getAwayFlag(teamID: Int){
        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamFlag(teamID.toString())).await(),
                TeamResponse::class.java)
            view.showAwayFlag(data.teams)
        }
    }
}
package com.rubahapi.footballapps.home.fragment.team

import com.google.gson.Gson
import com.rubahapi.footballapps.api.ApiRepository
import com.rubahapi.footballapps.api.TheSportDBApi
import com.rubahapi.footballapps.models.LeagueResponse
import com.rubahapi.footballapps.models.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter(private val view: TeamFragment,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson){
    fun getLeague(){
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getLeagues()).await(),
                LeagueResponse::class.java)
            view.showLeagueList(data)
            view.hideLoading()
        }
    }

    fun getTeamLeague(name:String){
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamLeague(name)).await(),
                TeamResponse::class.java)
            view.showTeamLeague(data.teams)
            view.hideLoading()
        }
    }

//    fun getMatch(leagueID:Int){
//        view.showLoading()
//        GlobalScope.launch(Dispatchers.Main){
//            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNextMatch(leagueID.toString())).await(),
//                MatchResponse::class.java)
//            view.showNextMatch(data.nextMatches)
//            view.hideLoading()
//        }
//    }
}
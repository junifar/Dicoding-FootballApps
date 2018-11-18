package com.rubahapi.footballapps.home.fragment.match.pastmatch

import com.google.gson.Gson
import com.rubahapi.footballapps.api.ApiRepository
import com.rubahapi.footballapps.api.TheSportDBApi
import com.rubahapi.footballapps.models.LeagueResponse
import com.rubahapi.footballapps.models.MatchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PastMatchPresenter(private val view: PastMatchView,
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

    fun getMatch(leagueID:Int){
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getLastMatch(leagueID.toString())).await(),
                MatchResponse::class.java)
            view.showPastMatch(data.nextMatches)
            view.hideLoading()
        }
    }
}
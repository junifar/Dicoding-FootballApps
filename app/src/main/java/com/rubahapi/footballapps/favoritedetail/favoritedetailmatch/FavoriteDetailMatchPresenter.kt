package com.rubahapi.footballapps.favoritedetail.favoritedetailmatch

import com.google.gson.Gson
import com.rubahapi.footballapps.api.ApiRepository
import com.rubahapi.footballapps.api.TheSportDBApi
import com.rubahapi.footballapps.models.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteDetailMatchPresenter(private val view: FavoriteDetailMatchView,
                                   private val apiRepository: ApiRepository,
                                   private val gson: Gson){
    fun getHomeFlag(teamID: String){
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamFlag(teamID)).await(),
                TeamResponse::class.java)
            view.showHomeFlag(data.teams)
        }
    }

//    suspend fun showAwayFlagAwait(data: TeamResponse){
//        view.showAwayFlag(data.teams)
//    }

    fun getAwayFlag(teamID: String){
//        val data = GlobalScope.async { gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamFlag(teamID)).await(),
//            TeamResponse::class.java) }
//
//        GlobalScope.async {
//            view.showAwayFlag(data.await().teams)
//        }
//
//        GlobalScope.async {
//            view.showHomeFlag(data.await().teams)
//        }
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamFlag(teamID)).await(),
                TeamResponse::class.java)

            view.showAwayFlag(data.teams)
//            view.showHomeFlag(data.teams)
        }
    }
}
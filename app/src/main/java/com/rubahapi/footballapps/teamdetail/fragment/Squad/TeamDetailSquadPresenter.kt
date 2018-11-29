package com.rubahapi.footballapps.teamdetail.fragment.Squad

import com.google.gson.Gson
import com.rubahapi.footballapps.api.ApiRepository
import com.rubahapi.footballapps.api.TheSportDBApi
import com.rubahapi.footballapps.models.PlayerResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailSquadPresenter(private val view: TeamDetailSquadFragment,
                               private val apiRepository: ApiRepository,
                               private val gson: Gson){
    fun getPlayer(id:String){
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getPlayers(id)).await(),
                PlayerResponse::class.java)
            view.showPlayer(data.players)
            view.hideLoading()
        }
    }
}
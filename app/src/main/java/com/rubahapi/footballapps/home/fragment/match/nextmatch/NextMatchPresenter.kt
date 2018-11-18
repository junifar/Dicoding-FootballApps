package com.rubahapi.footballapps.home.fragment.match.nextmatch

import com.google.gson.Gson
import com.rubahapi.footballapps.api.ApiRepository
import com.rubahapi.footballapps.api.TheSportDBApi
import com.rubahapi.footballapps.models.LeagueResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NextMatchPresenter(private val view: NextMatchView,
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
}
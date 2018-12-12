package com.rubahapi.footballapps.home.fragment.match.pastmatch

import android.support.v4.app.Fragment
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
                         private val gson: Gson):PastMatchPresenterView<PastMatchFragment>{
    private var mView: Fragment? = null

    override fun onAttach(view: PastMatchFragment) {
        mView = view
    }

    override fun onDetach() {
        mView = null
    }

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
            if(data.nextMatches.isNullOrEmpty()) view.showBlankMatch() else view.showPastMatch(data.nextMatches)
            view.hideLoading()
        }
    }
}
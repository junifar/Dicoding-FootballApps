package com.rubahapi.footballapps.home.fragment.match.pastmatch

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import com.rubahapi.footballapps.R
import com.rubahapi.footballapps.api.ApiRepository
import com.rubahapi.footballapps.matchdetail.pastmatch.PastMatchDetailActivity
import com.rubahapi.footballapps.models.League
import com.rubahapi.footballapps.models.LeagueResponse
import com.rubahapi.footballapps.models.Match
import com.rubahapi.footballapps.util.invisible
import com.rubahapi.footballapps.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class PastMatchFragment: Fragment(), AnkoComponent<Context>, PastMatchView{

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner
    private lateinit var league: League
    private lateinit var matchRecyclerView: RecyclerView

    private lateinit var presenter: PastMatchPresenter
    private lateinit var pastMatchAdapter: PastMatchAdapter
    private var pastMatches: MutableList<Match> = mutableListOf()
    private lateinit var dataListMatch:List<Match>

    override fun createView(ui: AnkoContext<Context>):View{
        return setupUI(ui)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        callConfiguration()
    }

    private fun callConfiguration(){
        val request = ApiRepository()
        val gson = Gson()
        presenter = PastMatchPresenter(this, request, gson)
        presenter.getLeague()

        swipeRefresh.setOnRefreshListener {
            presenter.getLeague()
            swipeRefresh.isRefreshing = false
        }

        pastMatchAdapter = PastMatchAdapter(pastMatches) {
            startActivity<PastMatchDetailActivity>("item" to it)
        }

        matchRecyclerView.adapter = pastMatchAdapter
    }

    private fun setupUI(ui:AnkoContext<Context>) = with(ui){
        verticalLayout{
            lparams(
                width = matchParent,
                height = matchParent
            )
            spinner = spinner()

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)
                relativeLayout {
                    lparams{
                        width = matchParent
                        height = wrapContent
                    }

                    matchRecyclerView = recyclerView {
                        lparams(
                            width = matchParent,
                            height =  matchParent
                        )
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                        visibility = View.INVISIBLE
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLeagueList(data: LeagueResponse) {
        if (activity != null){
        spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, data.leagues)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                league = spinner.selectedItem as League
                league.leagueId?.let { presenter.getMatch(it) }
            }
        }}
    }

    fun filterList(textFilter:String){
        val dataFilter = dataListMatch.filter { it.eventName?.contains(textFilter, true)?:false }
        pastMatches.clear()
        pastMatches.addAll(dataFilter)
        pastMatchAdapter.notifyDataSetChanged()
    }

    override fun showPastMatch(data: List<Match>) {
        dataListMatch = data
        swipeRefresh.isRefreshing = false
        pastMatches.clear()
        pastMatches.addAll(data)
        pastMatchAdapter.notifyDataSetChanged()
    }

    override fun showBlankMatch() {
        swipeRefresh.isRefreshing = false
        dataListMatch = mutableListOf()
        pastMatches.clear()
        pastMatchAdapter.notifyDataSetChanged()
    }
}
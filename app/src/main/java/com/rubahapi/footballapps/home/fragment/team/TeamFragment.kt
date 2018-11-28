package com.rubahapi.footballapps.home.fragment.team

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
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
import com.rubahapi.footballapps.models.League
import com.rubahapi.footballapps.models.LeagueResponse
import com.rubahapi.footballapps.models.Team
import com.rubahapi.footballapps.teamdetail.TeamDetailActivity
import com.rubahapi.footballapps.util.invisible
import com.rubahapi.footballapps.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamFragment:Fragment(), AnkoComponent<Context>, TeamView {
    lateinit var appBarLayout: AppBarLayout

    private lateinit var spinner: Spinner
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var league:League
    private lateinit var teamRecyclerView: RecyclerView
    private lateinit var teamLeagueAdapter: TeamAdapter

    private lateinit var presenter: TeamPresenter
    private var teamLeague: MutableList<Team> = mutableListOf()
    private lateinit var teamData:List<Team>

    override fun createView(ui: AnkoContext<Context>): View {
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
        presenter = TeamPresenter(this, request, gson)
        presenter.getLeague()

        swipeRefresh.setOnRefreshListener {
            presenter.getLeague()
            swipeRefresh.isRefreshing = false
        }

        teamLeagueAdapter = TeamAdapter(teamLeague){
            startActivity<TeamDetailActivity>("item" to it)
        }

        teamRecyclerView.adapter = teamLeagueAdapter
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLeagueList(data: LeagueResponse) {
        spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, data.leagues)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                league = spinner.selectedItem as League
                league.leagueName?.let { presenter.getTeamLeague(it) }
            }
        }
    }

    override fun showTeamLeague(data: List<Team>) {
        teamData = data
        swipeRefresh.isRefreshing = false
        teamLeague.clear()
        teamLeague.addAll(data)
        teamLeagueAdapter.notifyDataSetChanged()
    }

    fun FilterList(textFilter:String){
        val dataFilter = teamData.filter { it.teamName?.contains(textFilter, true)?:false }
        teamLeague.clear()
        teamLeague.addAll(dataFilter)
        teamLeagueAdapter.notifyDataSetChanged()
    }

    private fun setupUI(ui:AnkoContext<Context>) = with(ui){
        verticalLayout {
            lparams(
                width = matchParent,
                height = matchParent
            )

            fitsSystemWindows = false

            appBarLayout = themedAppBarLayout(R.style.AppTheme_AppBarOverlay){
                lparams(
                    width = matchParent,
                    height = wrapContent
                )

                themedToolbar(R.style.AppTheme_PopupOverlay) {
                    lparams(
                        width = matchParent,
                        height = android.R.attr.actionBarSize
                    )
                    title = "Football Apps"
                    setTitleTextColor(Color.WHITE)
                    visibility = View.GONE

                }.lparams{
                    weight = 1f
                }
            }

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

                    teamRecyclerView = recyclerView {
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

//            textView {
//                text = "Team test"
//            }
        }
    }
}
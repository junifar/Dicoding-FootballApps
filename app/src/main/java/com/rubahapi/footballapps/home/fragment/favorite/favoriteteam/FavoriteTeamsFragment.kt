package com.rubahapi.footballapps.home.fragment.favorite.favoriteteam

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.rubahapi.footballapps.R
import com.rubahapi.footballapps.db.Teams
import com.rubahapi.footballapps.db.database
import com.rubahapi.footballapps.models.Team
import com.rubahapi.footballapps.teamdetail.TeamDetailActivity
import com.rubahapi.footballapps.util.invisible
import com.rubahapi.footballapps.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.toast

class FavoriteTeamsFragment: Fragment(), AnkoComponent<Context>, FavoriteTeamView{

    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var teamRecyclerView: RecyclerView
    private lateinit var adapter: FavoriteTeamAdapter

    private var favoriteTeam:MutableList<Teams> = mutableListOf()

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

    override fun onResume() {
        super.onResume()
        favoriteTeam.clear()
        showFavorite()
    }

    private fun showFavorite() {
        showLoading()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Teams.TABLE_TEAM)
            val favorite = result.parseList(classParser<Teams>())
            favoriteTeam.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
        hideLoading()
    }

    private fun callConfiguration() {
        adapter = FavoriteTeamAdapter(favoriteTeam){
//            startActivity<FavoriteDetailMatchActivity>("id" to it.eventID)
            var team:Team = Team(it.teamID, it.teamName, it.teamBadge, it.intFormedYear?.toInt(), it.strStadium, it.strDescription)
            startActivity<TeamDetailActivity>("item" to team)
//            toast(it.teamName.toString())
        }

        teamRecyclerView.adapter = adapter
        showFavorite()

        swipeRefresh.setOnRefreshListener {
            favoriteTeam.clear()
            showFavorite()
        }
    }

    private fun setupUI(ui:AnkoContext<Context>) = with(ui){
        verticalLayout {
            lparams {
                width = matchParent
                height = matchParent
            }

            fitsSystemWindows = false


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
        }
//        coordinatorLayout{
//            lparams(
//                width = matchParent,
//                height = matchParent
//            )
//            textView {
//                text = "Favorite Teams Fragment"
//            }
//        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    companion object {
        fun newInstance(): FavoriteTeamsFragment {
            val fragment = FavoriteTeamsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}
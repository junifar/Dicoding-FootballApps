package com.rubahapi.footballapps.teamdetail.fragment.Squad

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
import com.google.gson.Gson
import com.rubahapi.footballapps.R
import com.rubahapi.footballapps.api.ApiRepository
import com.rubahapi.footballapps.models.Player
import com.rubahapi.footballapps.util.invisible
import com.rubahapi.footballapps.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.toast

class TeamDetailSquadFragment : Fragment(), AnkoComponent<Context>, TeamDetailSquadView {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var playerRecyclerView: RecyclerView

    private lateinit var presenter: TeamDetailSquadPresenter
    private var players:MutableList<Player> = mutableListOf()
    private lateinit var playerAdapter: PlayerAdapter

    private lateinit var id:String

    override fun createView(ui: AnkoContext<Context>): View {
        return setupUI(ui)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    private fun callConfiguration(){
        id = arguments?.getString("id") ?: "0"

        val request = ApiRepository()
        val gson = Gson()

        presenter = TeamDetailSquadPresenter(this, request, gson)
        presenter.getPlayer(id)

        swipeRefresh.setOnRefreshListener {
            presenter.getPlayer(id)
            swipeRefresh.isRefreshing = false
        }

        playerAdapter = PlayerAdapter(players){
            toast(it.idPlayer.toString())
        }

        playerRecyclerView.adapter = playerAdapter
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        callConfiguration()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayer(data: List<Player>) {
        swipeRefresh.isRefreshing = false
        players.clear()
        players.addAll(data)
        playerAdapter.notifyDataSetChanged()
    }

    private fun setupUI(ui:AnkoContext<Context>) = with(ui){
        verticalLayout {
            lparams{
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

                    playerRecyclerView = recyclerView {
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
//                text = "Team Detail Squad Fragment"
//            }
        }
    }

    companion object {
        fun newInstance(id:String): TeamDetailSquadFragment {
            val fragment = TeamDetailSquadFragment()
            val args = Bundle()
            args.putString("id", id)
            fragment.arguments = args
            return fragment
        }
    }

}

package com.rubahapi.footballapps.teamdetail.fragment.Description

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
import com.rubahapi.footballapps.models.Team
import com.rubahapi.footballapps.util.invisible
import com.rubahapi.footballapps.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamDetailDescriptionFragment : Fragment(), AnkoComponent<Context>, TeamDetailDescriptionView {

//    lateinit var textDescription: TextView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var teamRecyclerView: RecyclerView

    private lateinit var presenter: TeamDetailDescriptionPresenter

    private lateinit var teamLeagueAdapter: TeamDetailDescriptionAdapter
    private var teamLeague: MutableList<Team> = mutableListOf()

    override fun createView(ui: AnkoContext<Context>): View {
        return setupUI(ui)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    private fun callConfiguration(){
//        textDescription.text = arguments?.getString("description") ?: ""
        presenter = TeamDetailDescriptionPresenter(this)
        presenter.getTeamLeague(arguments?.getString("description") ?: "")

        swipeRefresh.setOnRefreshListener {
            presenter.getTeamLeague(arguments?.getString("description") ?: "")
            swipeRefresh.isRefreshing = false
        }

        teamLeagueAdapter = TeamDetailDescriptionAdapter(teamLeague){}
        teamRecyclerView.adapter = teamLeagueAdapter
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

    override fun showTeamLeague(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teamLeague.clear()
        teamLeague.addAll(data)
        teamLeagueAdapter.notifyDataSetChanged()
    }

    private fun setupUI(ui:AnkoContext<Context>) = with(ui){
        verticalLayout {
            lparams{
                width = matchParent
                height = matchParent
            }

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

//            scrollView {
//                lparams{
//                    width = matchParent
//                    height = matchParent
//                }
//
//
//                textDescription = textView {
//                    text = "Team Detail Description Fragment"
//                }
//
//            }
        }
    }

    companion object {
        fun newInstance(description:String): TeamDetailDescriptionFragment {
            val fragment = TeamDetailDescriptionFragment()
            val args = Bundle()
            args.putString("description", description)
            fragment.arguments = args
            return fragment
        }
    }

}

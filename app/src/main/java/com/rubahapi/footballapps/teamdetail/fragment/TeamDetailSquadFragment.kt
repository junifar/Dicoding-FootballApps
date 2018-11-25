package com.rubahapi.footballapps.teamdetail.fragment

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
import com.rubahapi.footballapps.R.color.colorAccent
import com.rubahapi.footballapps.api.ApiRepository
import com.rubahapi.footballapps.matchdetail.nextmatch.NextMatchDetailActivity
import com.rubahapi.footballapps.models.League
import com.rubahapi.footballapps.models.LeagueResponse
import com.rubahapi.footballapps.models.Match
import com.rubahapi.footballapps.util.invisible
import com.rubahapi.footballapps.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamDetailSquadFragment : Fragment(), AnkoComponent<Context> {

    override fun createView(ui: AnkoContext<Context>): View {
        return setupUI(ui)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    private fun callConfiguration(){

    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        callConfiguration()
    }

    private fun setupUI(ui:AnkoContext<Context>) = with(ui){
        verticalLayout {
            lparams{
                width = matchParent
                height = matchParent
            }

            textView {
                text = "Team Detail Squad Fragment"
            }
        }
    }

    companion object {
        fun newInstance(): TeamDetailSquadFragment {
            val fragment = TeamDetailSquadFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}

package com.rubahapi.footballapps.home.fragment.match.nextmatch

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
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
import com.rubahapi.footballapps.models.League
import com.rubahapi.footballapps.models.LeagueResponse
import com.rubahapi.footballapps.util.invisible
import com.rubahapi.footballapps.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.toast

class NextMatchFragment : Fragment(), AnkoComponent<Context>, NextMatchView {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner
    private lateinit var league:League

    private lateinit var presenter: NextMatchPresenter

    override fun createView(ui: AnkoContext<Context>): View {
        return setupUI(ui)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    private fun callConfiguration(){
        val request = ApiRepository()
        val gson = Gson()
        presenter = NextMatchPresenter(this, request, gson)
        presenter.getLeague()

        swipeRefresh.setOnRefreshListener {
            presenter.getLeague()
            swipeRefresh.isRefreshing = false
        }
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

            spinner = spinner()

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)
                relativeLayout {
                    lparams{
                        width = matchParent
                        height = wrapContent
                    }

                    textView { text = "Next Match" }

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
        spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, data.leagues)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                league = spinner.selectedItem as League
                toast(league.leagueId.toString())
            }

        }
    }

    companion object {
        fun newInstance(): NextMatchFragment {
            val fragment = NextMatchFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}

package com.rubahapi.footballapps.home.fragment.favorite.favoritematch

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
import com.rubahapi.footballapps.R.color.colorAccent
import com.rubahapi.footballapps.db.Favorite
import com.rubahapi.footballapps.db.database
import com.rubahapi.footballapps.favoritedetail.favoritedetailmatch.FavoriteDetailMatchActivity
import com.rubahapi.footballapps.util.invisible
import com.rubahapi.footballapps.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteMatchesFragment : Fragment(), AnkoComponent<Context>, FavoriteView {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var favoriteList: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: FavoriteAdapter

    private var favorites:MutableList<Favorite> = mutableListOf()
    lateinit var dataListMatch:List<Favorite>

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

    override fun onResume() {
        super.onResume()
        favorites.clear()
        showFavorite()
    }

    private fun callConfiguration(){
        adapter = FavoriteAdapter(favorites){
            startActivity<FavoriteDetailMatchActivity>("id" to it.eventID)
        }

        favoriteList.adapter = adapter
        showFavorite()

        swipeRefresh.setOnRefreshListener {
            favorites.clear()
            showFavorite()
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    private fun showFavorite(){
        showLoading()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            dataListMatch = favorite
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
        hideLoading()
    }

    fun FilterList(textFilter:String){
        val dataFilter = dataListMatch.filter { it.eventName?.contains(textFilter, true)?:false }
        favorites.clear()
        favorites.addAll(dataFilter)
        adapter.notifyDataSetChanged()
    }

    private fun setupUI(ui:AnkoContext<Context>) = with(ui){
        linearLayout {
            lparams{
                width = matchParent
                height = wrapContent
            }
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                relativeLayout {
                    lparams(
                        width = matchParent,
                        height = wrapContent
                    )
                    favoriteList = recyclerView {
                        lparams(
                            width = matchParent,
                            height = wrapContent
                        )

                        layoutManager = LinearLayoutManager(ctx)
                    }
                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance(): FavoriteMatchesFragment {
            val fragment = FavoriteMatchesFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}

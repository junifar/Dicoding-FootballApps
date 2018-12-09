package com.rubahapi.footballapps.home

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.rubahapi.footballapps.R
import com.rubahapi.footballapps.home.fragment.FavoriteFragment
import com.rubahapi.footballapps.home.fragment.MatchFragment
import com.rubahapi.footballapps.home.fragment.team.TeamFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    var savedInstanceState: Bundle? = null
    private lateinit var page:String

    private val pageArray = arrayOf("match", "teams", "favorite")

    lateinit var menuItem: MenuItem

    private var hideMenu = false

    private val fm = supportFragmentManager

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_match -> {
                page = pageArray[0]
                loadMatchFragment(savedInstanceState)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_teams -> {
                page = pageArray[1]
                loadTeamFragment(savedInstanceState)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                page = pageArray[2]
                loadFavoriteFragment(savedInstanceState)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        when(page){
            pageArray[0]->
                        {
                            val fragment = fm.findFragmentById(R.id.home_container) as MatchFragment
                            if (query != null)
                                fragment.filterList(query)

                        }
            pageArray[1]->
                        {
                            val fragment = fm.findFragmentById(R.id.home_container) as TeamFragment
                            if (query!=null)
                                fragment.filterList(query)
                        }
            else-> {
//                    hideMenu = true
//                    invalidateOptionsMenu()
//                menuItem.isEnabled = false
                    val fragment = fm.findFragmentById(R.id.home_container) as FavoriteFragment
                    if (query!=null)
                            fragment.filterList(query)
                }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView

        menuItem = menu.findItem(R.id.action_search) as MenuItem

//        if(hideMenu) menuItem.isVisible = false

        searchView.setSearchableInfo(
            searchManager
                .getSearchableInfo(componentName)
        )
        searchView.maxWidth = Integer.MAX_VALUE

        searchView.setOnQueryTextListener(this)
        return true
    }

    private fun loadMatchFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            val fragment = MatchFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.home_container, fragment, MatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadTeamFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            val fragment = TeamFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.home_container, fragment, TeamFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            val fragment = FavoriteFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.home_container, fragment, FavoriteFragment::class.java.simpleName)
                .commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        this.savedInstanceState = savedInstanceState

        loadMatchFragment(savedInstanceState)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        page = pageArray[0]
    }
}

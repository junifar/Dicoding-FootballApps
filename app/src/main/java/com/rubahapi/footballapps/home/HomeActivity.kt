package com.rubahapi.footballapps.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.rubahapi.footballapps.R
import com.rubahapi.footballapps.home.fragment.FavoriteFragment
import com.rubahapi.footballapps.home.fragment.MatchFragment
import com.rubahapi.footballapps.home.fragment.TeamFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    var savedInstanceState: Bundle? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_match -> {
                loadMatchFragment(savedInstanceState)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_teams -> {
                loadTeamFragment(savedInstanceState)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                loadFavoriteFragment(savedInstanceState)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
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
    }
}

package com.rubahapi.footballapps.teamdetail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.rubahapi.footballapps.R
import com.rubahapi.footballapps.db.Teams
import com.rubahapi.footballapps.db.database
import com.rubahapi.footballapps.models.Team
import com.rubahapi.footballapps.teamdetail.fragment.description.TeamDetailDescriptionFragment
import com.rubahapi.footballapps.teamdetail.fragment.squad.TeamDetailSquadFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class TeamDetailActivity: AppCompatActivity(){

    lateinit var item:Team
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        item = intent.getParcelableExtra("item")

        Picasso.get().load(item.teamBadge).fit().into(imgThumb)
        str_team.text = item.teamName
        int_formed_year.text = item.intFormedYear.toString()
        str_stadium.text = item.strStadium

        id = item.teamId.toString()
        favoriteState()
    }

    private fun favoriteState(){
        database.use {
            val result = select(Teams.TABLE_TEAM)
                .whereArgs("(TEAM_ID = {id})",
                    "id" to id
                )
            val favorite = result.parseList(classParser<Teams>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(
                    Teams.TABLE_TEAM, "(TEAM_ID = {id})",
                    "id" to id)
            }
//            snackbar(scrollView, "Removed to favorite").show()
            toast("Removed to favorite")
        } catch (e: SQLiteConstraintException){
//            snackbar(scrollView, e.localizedMessage).show()
            toast(e.localizedMessage)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(Teams.TABLE_TEAM,
                    Teams.teamID to item.teamId,
                    Teams.teamName to item.teamName,
                    Teams.teamBadge to item.teamBadge,
                    Teams.intFormedYear to item.intFormedYear,
                    Teams.strStadium to item.strStadium,
                    Teams.strDescription to item.strDescriptionEN)
        }
//            snackbar(scrollView, "Added to favorite").show()
            toast("Added to favorite")
        } catch (e: SQLiteConstraintException){
//            snackbar(scrollView, e.localizedMessage).show()
            toast(e.localizedMessage)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.match_detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when(position){
                0-> TeamDetailDescriptionFragment.newInstance(item.strDescriptionEN?:"")
                else-> TeamDetailSquadFragment.newInstance((item.teamId?:0).toString())
            }

        }

        override fun getCount(): Int {
            return 2
        }

    }
}
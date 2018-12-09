package com.rubahapi.footballapps.favoritedetail.favoritedetailmatch

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.google.gson.Gson
import com.rubahapi.footballapps.R
import com.rubahapi.footballapps.api.ApiRepository
import com.rubahapi.footballapps.db.Favorite
import com.rubahapi.footballapps.db.database
import com.rubahapi.footballapps.models.Team
import com.rubahapi.footballapps.util.toSimpleString
import com.rubahapi.footballapps.util.toSimpleStringGMT
import com.rubahapi.footballapps.util.toSimpleTimeString
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import java.text.SimpleDateFormat
import java.util.*

class FavoriteDetailMatchActivity: AppCompatActivity(), FavoriteDetailMatchView{
    private lateinit var eventID: String
    private lateinit var item: Favorite

    private lateinit var presenter: FavoriteDetailMatchPresenter

    private lateinit var imageHomeMatch: ImageView
    private lateinit var imageAwayMatch: ImageView
    private lateinit var teamHomeName:TextView
    private lateinit var teamAwayName:TextView
    lateinit var scrollView: ScrollView

    private lateinit var dateEvent:TextView
    private lateinit var timeEvent:TextView

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        favorite = intent.getParcelableExtra("items")
        eventID = intent.getStringExtra("id")

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupAction()
        setupUI()
        setupUIVariable()
    }

    private fun setupUIVariable() {
        val eventDateVal:String? = "${item.eventDate} ${item.strTime}"

        if (!item.eventDate.isNullOrBlank() && !item.strTime.isNullOrBlank()){
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ssZZZ")
            dateFormat.timeZone = (TimeZone.getTimeZone("GMT"))

            val date = dateFormat.parse(eventDateVal)
            dateEvent.text = toSimpleStringGMT(date)
        }else if (!item.eventDate.isNullOrBlank()){
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val date = dateFormat.parse(item.eventDate)
            dateEvent.text = toSimpleString(date)
        }else{
            dateEvent.text = "-"
        }

        var strTime: String? = item.strTime

        if (strTime?.contains("+") == false){
            strTime = "$strTime+00:00"
        }

        if (!item.strTime.isNullOrBlank()){
            val timeFormat = SimpleDateFormat("HH:mm:ssZZZ")
            timeFormat.timeZone = (TimeZone.getTimeZone("GMT"))
            val timeDate = timeFormat.parse(strTime)
            timeEvent.text = toSimpleTimeString(timeDate)
        }else{
            timeEvent.text = "00:00"
        }
    }

    private fun setupAction(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE).columns(Favorite.ID, Favorite.eventName,
                Favorite.eventID, Favorite.homeTeam, Favorite.awayTeam, Favorite.homeScore,
                Favorite.awayScore, Favorite.homeShoot, Favorite.awayShoot, Favorite.eventDate,
                Favorite.eventThumb, Favorite.homeGoalKeeper, Favorite.awayGoalKeeper,
                Favorite.homeDefense, Favorite.awayDefense,
                Favorite.homeMidField, Favorite.awayMidField,
                Favorite.homeForward, Favorite.awayForward,
                Favorite.homeSubstitute, Favorite.awaySubstitute,
                Favorite.homeGoalDetails, Favorite.awayGoalDetails,
                Favorite.idHome,
                Favorite.idAway,
                Favorite.strTime)
                .whereArgs("(EVENT_ID = {id})",
                    "id" to eventID
                )
            val data = result.parseList(classParser<Favorite>())
            data.forEach {
                item = it
            }
        }

        val request = ApiRepository()
        val gson = Gson()
        presenter = FavoriteDetailMatchPresenter(this, request, gson)
        item.idHome?.let { presenter.getHomeFlag(it) }
        item.idAway?.let { presenter.getAwayFlag(it) }

        id = item.eventID.toString()
        favoriteState()
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE).columns(Favorite.ID, Favorite.eventName,
                Favorite.eventID, Favorite.homeTeam, Favorite.awayTeam, Favorite.homeScore,
                Favorite.awayScore, Favorite.homeShoot, Favorite.awayShoot, Favorite.eventDate,
                Favorite.eventThumb, Favorite.homeGoalKeeper, Favorite.awayGoalKeeper,
                Favorite.homeDefense, Favorite.awayDefense,
                Favorite.homeMidField, Favorite.awayMidField,
                Favorite.homeForward, Favorite.awayForward,
                Favorite.homeSubstitute, Favorite.awaySubstitute,
                Favorite.homeGoalDetails, Favorite.awayGoalDetails,
                Favorite.idHome,
                Favorite.idAway,
                Favorite.strTime)
                .whereArgs("(EVENT_ID = {id})",
                    "id" to id
                )
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
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

    private fun addToFavorite(){
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                    Favorite.eventID to item.eventID,
                    Favorite.homeTeam to item.homeTeam,
                    Favorite.awayTeam to item.awayTeam,
                    Favorite.homeScore to item.homeScore,
                    Favorite.awayScore to item.awayScore,
                    Favorite.homeShoot to item.homeShoot,
                    Favorite.awayShoot to item.awayShoot,
                    Favorite.eventDate to item.eventDate,
                    Favorite.eventThumb to item.eventThumb,
                    Favorite.homeGoalKeeper to item.homeGoalKeeper,
                    Favorite.awayGoalKeeper to item.awayGoalKeeper,
                    Favorite.homeDefense to item.homeDefense,
                    Favorite.awayDefense to item.awayDefense,
                    Favorite.homeMidField to item.homeMidField,
                    Favorite.awayMidField to item.awayMidField,
                    Favorite.homeForward to item.homeForward,
                    Favorite.awayForward to item.awayForward,
                    Favorite.homeSubstitute to item.homeSubstitute,
                    Favorite.awaySubstitute to item.awaySubstitute,
                    Favorite.homeGoalDetails to item.homeGoalDetails,
                    Favorite.awayGoalDetails to item.awayGoalDetails,
                    Favorite.idHome to item.idHome,
                    Favorite.idAway to item.idAway)
            }
            snackbar(scrollView, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(scrollView, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(
                    Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})",
                    "id" to id)
            }
            snackbar(scrollView, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(scrollView, e.localizedMessage).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.match_detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    override fun showHomeFlag(data: List<Team>) {
        var imgUrl = ""
        var teamName = ""
        data.forEach {
            imgUrl = it.teamBadge.toString()
            teamName = it.teamName.toString()
        }
        Picasso.get().load(imgUrl).fit().into(imageHomeMatch)
        teamHomeName.text = teamName
    }

    override fun showAwayFlag(data: List<Team>) {
        var imgUrl = ""
        var teamName = ""
        data.forEach {
            imgUrl = it.teamBadge.toString()
            teamName = it.teamName.toString()
        }
        Picasso.get().load(imgUrl).fit().into(imageAwayMatch)
        teamAwayName.text = teamName
    }

    private fun setupUI(){
        scrollView = scrollView{
            lparams(
                width = matchParent,
                height = matchParent
            )
            linearLayout {
                lparams(width = matchParent, height = matchParent)
                orientation = LinearLayout.VERTICAL

                linearLayout {
                    lparams(
                        width = matchParent,
                        height = wrapContent
                    ) {
                        margin = dip(20)
                    }
                    gravity = Gravity.CENTER
                    orientation = LinearLayout.HORIZONTAL

                    verticalLayout {
                        gravity = Gravity.CENTER
                        imageHomeMatch = imageView {
                        }.lparams(
                            width = dip(75),
                            height = dip(75)
                        )
                        teamHomeName = textView {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textColor = Color.GREEN
                        }.lparams(
                            width = dip(120),
                            height = wrapContent
                        )
                    }

                    textView {
                        text = "${item.homeScore}"
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        setTypeface(null, Typeface.BOLD)
                    }.lparams(
                        width = dip(40),
                        height = wrapContent
                    )

                    textView {
                        text = "VS"
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        setTypeface(null, Typeface.BOLD)
                    }.lparams(
                        width = dip(30),
                        height = wrapContent
                    )

                    textView {
                        text = "${item.awayScore}"
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        setTypeface(null, Typeface.BOLD)
                    }.lparams(
                        width = dip(40),
                        height = wrapContent
                    )

                    verticalLayout {
                        gravity = Gravity.CENTER
                        imageAwayMatch = imageView {
                        }.lparams(
                            width = dip(75),
                            height = dip(75)
                        )
                        teamAwayName = textView {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            textColor = Color.GREEN
                        }.lparams(
                            width = dip(120),
                            height = wrapContent
                        )
                    }

                }

                dateEvent = textView {
                    text = item.eventDate.toString()
                    textSize = 16f
                    textColor = Color.GREEN
                    setTypeface(null, Typeface.BOLD)
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }.lparams(
                    width = matchParent,
                    height = wrapContent
                ) {
                    margin = dip(10)
                }

                timeEvent = textView {
                    text = item.eventDate.toString()
                    textSize = 16f
                    textColor = Color.GREEN
                    setTypeface(null, Typeface.BOLD)
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }.lparams(
                    width = matchParent,
                    height = wrapContent
                ) {
                    margin = dip(10)
                }

                tableRow {
                    lparams(
                        width = matchParent,
                        height = dip(1)
                    )
                    backgroundColor = Color.GRAY
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent) {
                        margin = dip(5)
                    }
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER

                    textView {
                        text = item.homeGoalDetails
                    }.lparams(
                        width = dip(100),
                        height = wrapContent
                    )

                    textView {
                        text = "Goal"
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(
                        width = dip(120),
                        height = wrapContent
                    )

                    textView {
                        text = item.awayGoalDetails
                    }.lparams(
                        width = dip(100),
                        height = wrapContent
                    )
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent) {
                        margin = dip(5)
                    }
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER

                    textView {
                        text = item.homeShoot
                    }

                    textView {
                        text = "Shoot"
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(
                        width = dip(120),
                        height = wrapContent
                    )

                    textView {
                        text = item.awayShoot
                    }
                }

                tableRow {
                    lparams(
                        width = matchParent,
                        height = dip(1)
                    )
                    backgroundColor = Color.GRAY
                }

                textView {
                    text = "Lineups"
                    textSize = 16f
                    setTypeface(null, Typeface.BOLD)
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }.lparams(
                    width = matchParent,
                    height = wrapContent
                ) {
                    margin = dip(10)
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent) {
                        margin = dip(5)
                    }
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER

                    textView {
                        text = item.homeGoalKeeper
                    }.lparams(
                        width = dip(100),
                        height = wrapContent
                    )

                    textView {
                        text = "Goal Keeper"
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(
                        width = dip(120),
                        height = wrapContent
                    )

                    textView {
                        text = item.awayGoalKeeper
                    }.lparams(
                        width = dip(100),
                        height = wrapContent
                    )
                }

                tableRow {
                    lparams(
                        width = matchParent,
                        height = dip(1)
                    )
                    backgroundColor = Color.LTGRAY
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent) {
                        margin = dip(5)
                    }
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER

                    textView {
                        text = item.homeDefense
                    }.lparams(
                        width = dip(100),
                        height = wrapContent
                    )

                    textView {
                        text = "Defense"
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(
                        width = dip(120),
                        height = wrapContent
                    )

                    textView {
                        text = item.awayDefense
                    }.lparams(
                        width = dip(100),
                        height = wrapContent
                    )
                }

                tableRow {
                    lparams(
                        width = matchParent,
                        height = dip(1)
                    )
                    backgroundColor = Color.LTGRAY
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent) {
                        margin = dip(5)
                    }
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER

                    textView {
                        text = item.homeMidField
                    }.lparams(
                        width = dip(100),
                        height = wrapContent
                    )

                    textView {
                        text = "Middle Field"
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(
                        width = dip(120),
                        height = wrapContent
                    )

                    textView {
                        text = item.awayMidField
                    }.lparams(
                        width = dip(100),
                        height = wrapContent
                    )
                }

                tableRow {
                    lparams(
                        width = matchParent,
                        height = dip(1)
                    )
                    backgroundColor = Color.LTGRAY
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent) {
                        margin = dip(5)
                    }
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER

                    textView {
                        text = item.homeForward
                    }.lparams(
                        width = dip(100),
                        height = wrapContent
                    )

                    textView {
                        text = "Forward"
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(
                        width = dip(120),
                        height = wrapContent
                    )

                    textView {
                        text = item.awayForward
                    }.lparams(
                        width = dip(100),
                        height = wrapContent
                    )
                }

                tableRow {
                    lparams(
                        width = matchParent,
                        height = dip(1)
                    )
                    backgroundColor = Color.LTGRAY
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent) {
                        margin = dip(5)
                    }
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER

                    textView {
                        text = item.homeSubstitute
                    }.lparams(
                        width = dip(100),
                        height = wrapContent
                    )

                    textView {
                        text = "Substitute"
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(
                        width = dip(120),
                        height = wrapContent
                    )

                    textView {
                        text = item.awaySubstitute
                    }.lparams(
                        width = dip(100),
                        height = wrapContent
                    )
                }
                tableRow {
                    lparams(
                        width = matchParent,
                        height = dip(1)
                    )
                    backgroundColor = Color.LTGRAY
                }
            }
//            swipeRefresh = swipeRefreshLayout {
//                setColorSchemeResources(
//                    colorAccent,
//                    android.R.color.holo_green_light,
//                    android.R.color.holo_orange_light,
//                    android.R.color.holo_red_light
//                )
//
//            }

        }
    }
}
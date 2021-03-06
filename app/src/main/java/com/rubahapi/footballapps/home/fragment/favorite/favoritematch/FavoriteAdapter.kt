package com.rubahapi.footballapps.home.fragment.favorite.favoritematch

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.rubahapi.footballapps.R
import com.rubahapi.footballapps.R.id.event_date
import com.rubahapi.footballapps.db.Favorite
import com.rubahapi.footballapps.util.toSimpleString
import com.rubahapi.footballapps.util.toSimpleStringGMT
import com.rubahapi.footballapps.util.toSimpleTimeString
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import java.text.SimpleDateFormat
import java.util.*

class FavoriteAdapter(private val items: List<Favorite>,
                      private val listener: (Favorite) -> Unit): RecyclerView.Adapter<FavoriteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(FavoriteUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

}

class FavoriteUI: AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            cardView {
                padding = dip(5)
                cardElevation = dip(6).toFloat()
                radius = dip(5).toFloat()
                useCompatPadding = true
                lparams {
                    width = matchParent
                    height = wrapContent
                    radius = dip(4f).toFloat()
                }
                linearLayout {
                    lparams(
                        width = matchParent,
                        height = wrapContent
                    )
                    orientation = LinearLayout.VERTICAL

                    textView {
                        id = event_date
                        textSize = 14f
                        textColor = Color.GREEN
                        setTypeface(null, Typeface.BOLD)
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams {
                        margin = dip(5)
                        width = matchParent
                        height = wrapContent
                    }

                    textView {
                        id = R.id.event_time
                        textSize = 16f
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams {
                        margin = dip(5)
                        width = matchParent
                        height = wrapContent
                        padding = dip(0)
                    }

                    linearLayout {
                        lparams(
                            width = matchParent,
                            height = wrapContent
                        )
                        orientation = LinearLayout.HORIZONTAL
                        gravity = Gravity.CENTER

                        textView {
                            id = R.id.home_team
                            textSize = 16f
                        }.lparams {
                            margin = dip(15)
                        }
                        textView {
                            id = R.id.home_score
                            textSize = 16f
                        }.lparams {
                            margin = dip(15)
                        }
                        textView {
                            text = "VS"
                            textSize = 16f
                        }
                        textView {
                            id = R.id.away_score
                            textSize = 16f
                        }.lparams {
                            margin = dip(15)
                        }
                        textView {
                            id = R.id.away_team
                            textSize = 16f
                        }.lparams {
                            margin = dip(15)
                        }
                    }
                }
            }
        }
    }

}

class FavoriteViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val homeTeam: TextView = view.find(R.id.home_team)
    private val awayTeam: TextView = view.find(R.id.away_team)
    private val homeScore: TextView = view.find(R.id.home_score)
    private val awayScore: TextView = view.find(R.id.away_score)
    private val eventDate: TextView = view.find(event_date)
    private val eventTime: TextView = view.find(R.id.event_time)

    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {
        var strTime: String? = favorite.strTime

        var eventDateVal:String? = "${favorite.eventDate} ${favorite.strTime}"

        if (!favorite.eventDate.isNullOrBlank() && !favorite.strTime.isNullOrBlank()){
            if (strTime?.contains("+") == false){
                eventDateVal = "$eventDateVal+00:00"
            }

            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ssZZZ")
            dateFormat.timeZone = (TimeZone.getTimeZone("GMT"))

            val date = dateFormat.parse(eventDateVal)
            eventDate.text = toSimpleStringGMT(date)
        }else if (!favorite.eventDate.isNullOrBlank()){
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val date = dateFormat.parse(favorite.eventDate)
            eventDate.text = toSimpleString(date)
        }else{
            eventDate.text = "-"
        }

        if (strTime?.contains("+") == false){
            strTime = "$strTime+00:00"
        }

        if (!favorite.strTime.isNullOrBlank()){
            val timeFormat = SimpleDateFormat("HH:mm:ssZZZ")
            timeFormat.timeZone = (TimeZone.getTimeZone("GMT"))
            val timeDate = timeFormat.parse(strTime)
            eventTime.text = toSimpleTimeString(timeDate)
        }else{
            eventTime.text = "00:00"
        }

        homeTeam.text = favorite.homeTeam
        awayTeam.text = favorite.awayTeam
        homeScore.text = favorite.homeScore ?: "0"
        awayScore.text = favorite.awayScore ?: "0"
        itemView.setOnClickListener { listener(favorite) }
    }
}

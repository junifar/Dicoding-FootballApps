package com.rubahapi.footballapps.home.fragment.favorite.favoriteteam

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.rubahapi.footballapps.R
import com.rubahapi.footballapps.R.id.event_name
import com.rubahapi.footballapps.db.Teams
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class FavoriteTeamAdapter(private val items: List<Teams>,
                          private val listener: (Teams) -> Unit): RecyclerView.Adapter<TeamViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(
            TeamsUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

}

class TeamsUI : AnkoComponent<ViewGroup>{
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
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.event_thumb
                    }.lparams(
                        width = dip(75),
                        height = dip(75)
                    )

                    textView {
                        id = R.id.event_name
                        textSize = 16f
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams{
                        margin = dip(5)
                        width = wrapContent
                        height = wrapContent
                        padding= dip(0)
                        gravity = Gravity.CENTER_VERTICAL
                    }
                }
            }
        }
    }

}

class TeamViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val eventName: TextView = view.find(event_name)
    private val eventThumb: ImageView = view.find(R.id.event_thumb)
//    private val eventDate: TextView = view.find(event_date)

    fun bindItem(teamLeague: Teams, listener: (Teams)-> Unit){
//        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
//        val date = dateFormat.parse(teamLeague.eventDate)
        eventName.text = teamLeague.teamName
        Picasso.get().load(teamLeague.teamBadge).fit().into(eventThumb)
//        eventDate.text = toSimpleString(date)

        itemView.setOnClickListener { listener(teamLeague) }
    }

}


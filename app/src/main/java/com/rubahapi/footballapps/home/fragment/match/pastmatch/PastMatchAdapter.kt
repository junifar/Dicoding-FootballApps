package com.rubahapi.footballapps.home.fragment.match.pastmatch

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.rubahapi.footballapps.R
import com.rubahapi.footballapps.R.color.material_grey_300
import com.rubahapi.footballapps.models.Match
import com.rubahapi.footballapps.util.toSimpleString
import com.rubahapi.footballapps.R.id.event_date
import com.rubahapi.footballapps.R.id.event_name
import com.rubahapi.footballapps.util.toSimpleTimeString
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import java.text.SimpleDateFormat
import java.util.*

class PastMatchAdapter(private val items: List<Match>,
                       private val listener: (Match) -> Unit): RecyclerView.Adapter<NextMatchViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextMatchViewHolder {
        return NextMatchViewHolder(
            NextMatchesUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: NextMatchViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

}

class NextMatchesUI : AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            cardView {
                padding = dip(5)
                lparams {
                    width = matchParent
                    height = wrapContent
                }
                cardElevation = dip(6).toFloat()
                radius = dip(5).toFloat()
                useCompatPadding = true
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.VERTICAL

                    textView {
                        id = R.id.event_date
                        textSize = 14f
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textColor = Color.GREEN
                        setTypeface(null, Typeface.BOLD)
                    }.lparams{
                        margin = dip(5)
                        width = matchParent
                        height = wrapContent
                        padding = dip(0)
                    }

                    textView{
                        id = R.id.event_time
                        textSize = 16f
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams{
                        margin = dip(5)
                        width = matchParent
                        height = wrapContent
                        padding= dip(0)
                    }

                    textView {
                        id = R.id.event_name
                        textSize = 16f
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams{
                        margin = dip(5)
                        width = matchParent
                        height = wrapContent
                        padding= dip(0)
                    }
                    tableRow {
                        lparams(
                            width = matchParent,
                            height = dip(1)
                        )
                        backgroundColor = Color.LTGRAY
                    }
                }
            }
        }
    }

}

class NextMatchViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val eventName: TextView = view.find(event_name)
    private val eventDate: TextView = view.find(event_date)
    private val eventTime: TextView = view.find(R.id.event_time)

    fun bindItem(match: Match, listener: (Match)-> Unit){
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = dateFormat.parse(match.eventDate)
        val timeFormat = SimpleDateFormat("HH:mm:ssXXX")
        timeFormat.timeZone = (TimeZone.getTimeZone("GMT"))
        val timeDate = timeFormat.parse(match.strTime)

        eventName.text = match.eventName
        eventDate.text = toSimpleString(date)
        eventTime.text = toSimpleTimeString(timeDate)

        itemView.setOnClickListener { listener(match) }
    }

}


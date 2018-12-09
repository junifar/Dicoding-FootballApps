package com.rubahapi.footballapps.teamdetail.fragment.description

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rubahapi.footballapps.R
import com.rubahapi.footballapps.R.id.team_description
import com.rubahapi.footballapps.models.Team
import org.jetbrains.anko.*

class TeamDetailDescriptionAdapter(private val items: List<Team>,
                       private val listener: (Team) -> Unit): RecyclerView.Adapter<TeamViewHolder>(){

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
            linearLayout {
                lparams {
                    width = matchParent
                    height = wrapContent
                }
                textView {
                    id = R.id.team_description

                }.lparams {
                    width = matchParent
                    height = wrapContent
                }
            }
        }
    }

}

class TeamViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val teamDescription: TextView = view.find(team_description)

    fun bindItem(teamLeague: Team, listener: (Team)-> Unit){
        teamDescription.text = teamLeague.strDescriptionEN

        itemView.setOnClickListener { listener(teamLeague) }
    }

}


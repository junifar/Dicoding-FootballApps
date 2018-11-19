package com.rubahapi.footballapps.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favorite(val id: Long?,
                    val eventID: String? = null,
                    val homeTeam: String? = null,
                    val awayTeam: String? = null,
                    val homeScore: String? = null,
                    val awayScore: String? = null,
                    val homeShoot: String? = null,
                    val awayShoot: String? = null,
                    val eventDate: String? = null,
                    val eventThumb: String? = null,
                    val homeGoalKeeper: String? = null,
                    val awayGoalKeeper: String? = null,
                    val homeDefense: String? = null,
                    val awayDefense: String? = null,
                    val homeMidField: String? = null,
                    val awayMidField: String? = null,
                    val homeForward: String? = null,
                    val awayForward: String? = null,
                    val homeSubstitute: String? = null,
                    val awaySubstitute: String? = null,
                    val homeGoalDetails: String? = null,
                    val awayGoalDetails: String? = null,
                    val idHome: String? = null,
                    val idAway: String? = null
                    ) : Parcelable {
    companion object {
        const val TABLE_FAVORITE   = "TABLE_FAVORITE"
        const val ID               = "ID_"
        const val eventID          = "EVENT_ID"
        const val homeTeam         = "HOME_TEAM"
        const val awayTeam         = "AWAY_TEAM"
        const val homeScore        = "HOME_SCORE"
        const val awayScore        = "AWAY_SCORE"
        const val homeShoot        = "HOME_SHOOT"
        const val awayShoot        = "AWAY_SHOOT"
        const val eventDate        = "EVENT_DATE"
        const val eventThumb       = "EVENT_THUMB"
        const val homeGoalKeeper   = "HOME_GOAL_KEEPER"
        const val awayGoalKeeper   = "AWAY_GOAL_KEEPER"
        const val homeDefense      = "HOME_DEFENSE"
        const val awayDefense      = "AWAY_DEFENSE"
        const val homeMidField     = "HOME_MID_FIELD"
        const val awayMidField     = "AWAY_MID_FIELD"
        const val homeForward      = "HOME_FORWARD"
        const val awayForward      = "AWAY_FORWARD"
        const val homeSubstitute   = "HOME_SUBSTITUTE"
        const val awaySubstitute   = "AWAY_SUBSTITUTE"
        const val homeGoalDetails  = "HOME_GOAL_DETAILS"
        const val awayGoalDetails  = "AWAY_GOAL_DETAIL"
        const val idHome           = "ID_HOME"
        const val idAway           = "ID_AWAY"
    }
}
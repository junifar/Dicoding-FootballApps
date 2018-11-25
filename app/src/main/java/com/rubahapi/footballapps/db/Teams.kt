package com.rubahapi.footballapps.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Teams(val id: Long?,
                 val teamID: String? = null,
                 val teamName: String? = null,
                 val teamBadge: String? = null,
                 val intFormedYear: String? = null,
                 val strStadium: String? = null,
                 val strDescription: String? = null
                    ) : Parcelable {
    companion object {
        const val TABLE_TEAM   = "TABLE_TEAM"
        const val ID               = "ID_"
        const val teamID           = "TEAM_ID"
        const val teamName         = "TEAM_NAME"
        const val teamBadge        = "TEAM_BADGE"
        const val intFormedYear    = "FORMED_YEAR"
        const val strStadium       = "STADIUM"
        const val strDescription   = "DESCRIPTION"
    }
}
package com.rubahapi.footballapps.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(
    @SerializedName("idEvent")
    val eventID: Int? = 0,
    @SerializedName("strEvent")
    val eventName: String? = null,
    @SerializedName("dateEvent")
    val eventDate: String? = null,
    @SerializedName("strThumb")
    val eventThumb: String? = null,
    @SerializedName("intHomeScore")
    val homeScore: Int? = 0,
    @SerializedName("intAwayScore")
    val awayScore: Int? = 0,
    @SerializedName("idHomeTeam")
    val idHome: Int? = 0,
    @SerializedName("idAwayTeam")
    val idAway: Int? = 0,
    @SerializedName("strHomeTeam")
    val homeTeam: String? = null,
    @SerializedName("strAwayTeam")
    val awayTeam: String? = null,
    @SerializedName("intHomeShots")
    val homeShoot: String? = null,
    @SerializedName("intAwayShots")
    val awayShoot: String? = null,
    @SerializedName("strHomeLineupGoalkeeper")
    val homeGoalKeeper: String? = null,
    @SerializedName("strAwayLineupGoalkeeper")
    val awayGoalKeeper: String? = null,
    @SerializedName("strHomeLineupDefense")
    val homeDefense: String? = null,
    @SerializedName("strAwayLineupDefense")
    val awayDefense: String? = null,
    @SerializedName("strHomeLineupMidfield")
    val homeMidField: String? = null,
    @SerializedName("strAwayLineupMidfield")
    val awayMidField: String? = null,
    @SerializedName("strHomeLineupForward")
    val homeForward: String? = null,
    @SerializedName("strAwayLineupForward")
    val awayForward: String? = null,
    @SerializedName("strHomeLineupSubstitutes")
    val homeSubstitute: String? = null,
    @SerializedName("strAwayLineupSubstitutes")
    val awaySubstitute: String? = null,
    @SerializedName("strHomeGoalDetails")
    val homeGoalDetails: String? = null,
    @SerializedName("strAwayGoalDetails")
    val awayGoalDetails: String? = null,
    @SerializedName("strTime")
    val strTime:String? = null
) : Parcelable
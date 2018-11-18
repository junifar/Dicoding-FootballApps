package com.rubahapi.footballapps.api

import android.net.Uri
import com.rubahapi.footballapps.BuildConfig

object TheSportDBApi {

    fun getLeagues():String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("all_leagues.php")
            .build()
            .toString()
    }


}
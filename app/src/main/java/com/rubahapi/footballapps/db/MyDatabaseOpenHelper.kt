package com.rubahapi.footballapps.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by root on 2/6/18.
 */
class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db",
    null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.eventID to TEXT + UNIQUE,
                Favorite.eventName to TEXT,
                Favorite.homeTeam to TEXT,
                Favorite.awayTeam to TEXT,
                Favorite.homeScore to TEXT,
                Favorite.awayScore to TEXT,
                Favorite.homeShoot to TEXT,
                Favorite.awayShoot to TEXT,
                Favorite.eventDate to TEXT,
                Favorite.eventThumb to TEXT,
                Favorite.homeGoalKeeper to TEXT,
                Favorite.awayGoalKeeper to TEXT,
                Favorite.homeDefense to TEXT,
                Favorite.awayDefense to TEXT,
                Favorite.homeMidField to TEXT,
                Favorite.awayMidField to TEXT,
                Favorite.homeForward to TEXT,
                Favorite.awayForward to TEXT,
                Favorite.homeSubstitute to TEXT,
                Favorite.awaySubstitute to TEXT,
                Favorite.homeGoalDetails to TEXT,
                Favorite.awayGoalDetails to TEXT,
                Favorite.idHome to TEXT,
                Favorite.idAway to TEXT,
                Favorite.strTime to TEXT)

        db.createTable(Teams.TABLE_TEAM, true,
            Teams.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Teams.teamID to TEXT + UNIQUE,
            Teams.teamName to TEXT,
            Teams.teamBadge to TEXT,
            Teams.intFormedYear to TEXT,
            Teams.strStadium to TEXT,
            Teams.strDescription to TEXT
            )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE, true)
        db.dropTable(Teams.TABLE_TEAM, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)
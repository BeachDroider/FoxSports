package com.example.foad.foxsports.api

import com.example.foad.foxsports.BuildConfig
import com.example.foad.foxsports.data.MatchStat
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StatService {

    @GET("/3.0/api/sports/league/matches/NRL20172101/topplayerstats.json;type=fantasy_points;type=tackles;type=runs;type=run_metres")
    fun getMatchStats(
            @Query("limit") limit: Int = 5,
            @Query("userkey") userKey: String = BuildConfig.USER_KEY

    ): Call<List<MatchStat>>

}
package com.example.foad.foxsports.data

import com.google.gson.annotations.SerializedName

data class MatchStat(

        @SerializedName("match_id") val matchId: String,
        @SerializedName("team_A") val teamA: Team,
        @SerializedName("team_B") val teamB: Team,
        @SerializedName("stat_type") val statType: StatType
)
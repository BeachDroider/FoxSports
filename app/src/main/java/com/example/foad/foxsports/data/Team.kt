package com.example.foad.foxsports.data

import com.google.gson.annotations.SerializedName

data class Team(

        val id: Long,
        val name: String,
        val code: String,
        @SerializedName("short_name") val shortName: String,
        @SerializedName("top_players") val topPlayers: List<TopPlayer>
)
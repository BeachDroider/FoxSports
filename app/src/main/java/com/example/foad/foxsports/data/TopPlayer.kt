package com.example.foad.foxsports.data

import com.google.gson.annotations.SerializedName

data class TopPlayer(
        val id: Long,
        val position: String,
        @SerializedName("full_name") val fullName: String,
        @SerializedName("short_name") val shortName: String,
        @SerializedName("stat_value") val statValue: Int,
        @SerializedName("jumper_number") val jumperNumber: Int
)
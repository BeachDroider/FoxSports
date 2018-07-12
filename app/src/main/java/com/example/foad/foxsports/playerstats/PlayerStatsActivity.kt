package com.example.foad.foxsports.playerstats

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.foad.foxsports.R
import com.example.foad.foxsports.constants.IntentKeys
import kotlinx.android.synthetic.main.activity_player_stats.*

class PlayerStatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_stats)

        val playerId = intent.getStringExtra(IntentKeys.PLAYER_STATS_PLAYER_ID)
        val teamId = intent.getStringExtra(IntentKeys.PLAYER_STATS_TEAM_ID)

        tv_player_id.text = playerId
        tv_team_id.text = teamId
    }
}

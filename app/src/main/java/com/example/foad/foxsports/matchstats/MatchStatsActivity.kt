package com.example.foad.foxsports.matchstats

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.foad.foxsports.R
import com.example.foad.foxsports.constants.IntentKeys

import com.example.foad.foxsports.di.DaggerAppComponent
import com.example.foad.foxsports.playerstats.PlayerStatsActivity
import kotlinx.android.synthetic.main.activity_match_stats.*

import javax.inject.Inject

class MatchStatsActivity : AppCompatActivity(), MatchStatsAdapter.TopPlayerClickListener {

    lateinit var TAG: String

    @Inject
    lateinit var matchStatsRepository: MatchStatsRepository

    lateinit var adapter: MatchStatsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_stats)

        TAG = this::class.java.simpleName

        DaggerAppComponent.builder().application(application).build().inject(this)

        adapter = MatchStatsAdapter(this)
        rv_match_stats.adapter = adapter
        rv_match_stats.layoutManager = LinearLayoutManager(this)

        val viewModel = ViewModelProviders.of(this, MatchStatsViewModel.MatchStatViewModelFactory(application, matchStatsRepository))
                .get(MatchStatsViewModel::class.java)

        sw_main.setOnRefreshListener {
            viewModel.refresh()
        }

        viewModel.matchStatListViewModel.observe(this, Observer {
            adapter.viewModel = it
        })

        viewModel.loading.observe(this, Observer {
            it?.let { sw_main.isRefreshing = it }
        })


        viewModel.errorMessage.observe(this, Observer {
            it?.let {
                Log.e(TAG, "error message: $it")
            }
        })
    }

    override fun onPlayerClicked(playerId: String, teamId: String) {
        Intent(this, PlayerStatsActivity::class.java).apply {
            putExtra(IntentKeys.PLAYER_STATS_PLAYER_ID, playerId)
            putExtra(IntentKeys.PLAYER_STATS_TEAM_ID, teamId)
            startActivity(this)
        }
    }
}

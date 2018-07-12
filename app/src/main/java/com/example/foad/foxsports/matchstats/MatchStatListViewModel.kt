package com.example.foad.foxsports.matchstats

import com.example.foad.foxsports.BuildConfig
import com.example.foad.foxsports.data.MatchStat

class MatchStatListViewModel(val matchStats: List<MatchStat>) {

    val headerViewModel: MatchStatListHeaderViewModel
    val topPlayerViewModels: List<MatchStatTopPlayerListViewModel>

    init {
        headerViewModel = MatchStatListHeaderViewModel(matchStats[0].teamA.name, matchStats[0].teamB.name)
        topPlayerViewModels = matchStats.map {
            val topPlayerA = it.teamA.topPlayers.maxBy {
                it.statValue }
            val topPlayerB = it.teamB.topPlayers.maxBy { it.statValue }

            MatchStatTopPlayerListViewModel(
                    topPlayerA?.shortName,
                    topPlayerA?.jumperNumber.toString(),
                    topPlayerA?.position,
                    topPlayerA?.statValue.toString(),
                    topPlayerA?.id.let { BuildConfig.HEAD_SHOT_PATH + it + ".jpg" },
                    topPlayerB?.shortName,
                    topPlayerB?.jumperNumber.toString(),
                    topPlayerB?.position,
                    topPlayerB?.statValue.toString(),
                    topPlayerB?.id.let { BuildConfig.HEAD_SHOT_PATH + it + ".jpg" },
                    topPlayerA?.id.toString(),
                    matchStats[0].teamA.id.toString(),
                    topPlayerB?.id.toString(),
                    matchStats[0].teamB.id.toString()
                    )
        }
    }

     inner class MatchStatListHeaderViewModel(
            val teamAName: String,
            val teamBName: String
     )

    inner class MatchStatTopPlayerListViewModel(
            val shortNameA: String?,
            val jumperNumberA: String?,
            val positionA: String?,
            val statValueA: String?,
            val headShotUrlA: String?,
            val shortNameB: String?,
            val jumperNumberB: String?,
            val positionB: String?,
            val statValueB: String?,
            val headShotUrlB: String?,
            val playerIdA: String,
            val teamIdA: String,
            val playerIdB: String,
            val teamIdB: String
            )



}
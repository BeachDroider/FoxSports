package com.example.foad.foxsports.matchstats

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foad.foxsports.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_match_stat_header.view.*
import kotlinx.android.synthetic.main.row_match_stat_top_player.view.*

class MatchStatsAdapter(val topPlayerClickListener: TopPlayerClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var viewModel: MatchStatListViewModel? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    enum class ViewType(val type: Int) {
        HEADER(0), TOP_PLAYER(1)
    }

    override fun getItemCount(): Int {
        return viewModel?.topPlayerViewModels?.let {
            it.size + 1
        } ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) ViewType.HEADER.type else ViewType.TOP_PLAYER.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.HEADER.type -> MatchStatHeaderVH(LayoutInflater.from(parent.context).inflate(R.layout.row_match_stat_header, parent, false))
            else -> MatchStatTopPlayerVH(LayoutInflater.from(parent.context).inflate(R.layout.row_match_stat_top_player, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MatchStatHeaderVH -> {
                viewModel?.headerViewModel?.let {
                    holder.bind(it)

                }
            }
            is MatchStatTopPlayerVH -> {
                viewModel?.topPlayerViewModels?.get(position - 1)?.let {
                    holder.bind(it)
                }
            }
        }

    }

    inner class MatchStatHeaderVH(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(viewModel: MatchStatListViewModel.MatchStatListHeaderViewModel) {
            itemView.tv_team_a_name.text = viewModel.teamAName
            itemView.tv_team_b_name.text = viewModel.teamBName
        }
    }

    inner class MatchStatTopPlayerVH(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(viewModel: MatchStatListViewModel.MatchStatTopPlayerListViewModel) {
            with(itemView) {
                tv_top_player_a_name.text = viewModel.shortNameA
                tv_top_player_b_name.text = viewModel.shortNameB
                tv_top_player_a_jumper_number.text = viewModel.jumperNumberA
                tv_top_player_b_jumper_number.text = viewModel.jumperNumberB
                tv_top_player_a_position.text = viewModel.positionA
                tv_top_player_b_position.text = viewModel.positionB
                tv_top_player_a_stat_value.text = viewModel.statValueA
                tv_top_player_b_stat_value.text = viewModel.statValueB

                viewModel.headShotUrlA?.let {
                    Picasso.with(context).load(it).into(iv_top_player_a_head_shot)
                }
                viewModel.headShotUrlB?.let {
                    Picasso.with(context).load(it).into(iv_top_player_b_head_shot)
                }

                iv_top_player_a_head_shot.setOnClickListener{
                    topPlayerClickListener.onPlayerClicked(viewModel.playerIdA, viewModel.teamIdA)
                }

                iv_top_player_b_head_shot.setOnClickListener{
                    topPlayerClickListener.onPlayerClicked(viewModel.playerIdB, viewModel.teamIdB)
                }

            }
        }
    }

    interface TopPlayerClickListener{
        fun onPlayerClicked(playerId: String, teamId: String)
    }

}
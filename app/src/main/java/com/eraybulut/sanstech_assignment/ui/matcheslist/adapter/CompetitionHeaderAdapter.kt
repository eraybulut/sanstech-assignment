package com.eraybulut.sanstech_assignment.ui.matcheslist.adapter

/**
 * Created by Eray BULUT on 2.03.2024
 * eraybulutlar@gmail.com
 */

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eraybulut.sanstech_assignment.databinding.ItemCompetitionHeaderBinding
import com.eraybulut.sanstech_assignment.ui.matcheslist.LeagueMatchesUIModel
import com.eraybulut.sanstech_assignment.ui.matcheslist.MatchEventListener
import com.eraybulut.sanstech_assignment.utils.extensions.inflateBinding
import com.eraybulut.sanstech_assignment.utils.extensions.loadUrl
import javax.inject.Inject

class CompetitionHeaderAdapter @Inject constructor() :
    ListAdapter<LeagueMatchesUIModel, CompetitionHeaderAdapter.CardViewHolder>(DIFF_CALLBACK) {

    private var onMatchClickListener: ((MatchEventListener) -> Unit)? = null

    fun setOnMatchClickListener(listener: (MatchEventListener) -> Unit) {
        onMatchClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder =
        CardViewHolder(
            parent.inflateBinding(ItemCompetitionHeaderBinding::inflate) as ItemCompetitionHeaderBinding
        )

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(match = getItem(position))
    }

    inner class CardViewHolder(private val binding: ItemCompetitionHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val matchesAdapter = MatchesAdapter().apply {
            setOnMatchClickListener {
                onMatchClickListener?.invoke(it)
            }
        }

        init {
            binding.rvMatch.apply {
                setHasFixedSize(true)
                itemAnimator = null
                adapter = matchesAdapter
            }
        }

        fun bind(match: LeagueMatchesUIModel) {
            with(binding) {
                txtLeagueName.text = match.leagueName
                icFlag.loadUrl(match.flag)
                matchesAdapter.submitList(match.matches)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LeagueMatchesUIModel>() {
            override fun areItemsTheSame(
                oldItem: LeagueMatchesUIModel, newItem: LeagueMatchesUIModel
            ): Boolean {
                return oldItem.leagueId == newItem.leagueId
            }

            override fun areContentsTheSame(
                oldItem: LeagueMatchesUIModel, newItem: LeagueMatchesUIModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
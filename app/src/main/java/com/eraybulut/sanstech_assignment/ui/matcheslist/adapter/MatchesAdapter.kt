package com.eraybulut.sanstech_assignment.ui.matcheslist.adapter

/**
 * Created by Eray BULUT on 2.03.2024
 * eraybulutlar@gmail.com
 */

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eraybulut.sanstech_assignment.R
import com.eraybulut.sanstech_assignment.databinding.ItemMatchBinding
import com.eraybulut.sanstech_assignment.ui.matcheslist.MatchEventListener
import com.eraybulut.sanstech_assignment.ui.matcheslist.MatchesItemUIModel
import com.eraybulut.sanstech_assignment.utils.extensions.inflateBinding
import com.eraybulut.sanstech_assignment.utils.extensions.setVisibilityOnCondition
import com.eraybulut.sanstech_assignment.utils.extensions.vibrateOnClick

class MatchesAdapter :
    ListAdapter<MatchesItemUIModel, MatchesAdapter.CardViewHolder>(DIFF_CALLBACK) {

    private var onMatchClickListener: ((MatchEventListener) -> Unit)? = null

    fun setOnMatchClickListener(listener: (MatchEventListener) -> Unit) {
        onMatchClickListener = listener
    }

    init {
        setHasStableIds(true)
    }

    inner class CardViewHolder(private val binding: ItemMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(match: MatchesItemUIModel, isLastItem: Boolean) {
            with(binding) {
                txtHomeTeam.text = match.homeTeamName
                txtAwayTeam.text = match.awayTeamName
                txtAbbr.text = match.matchAbbr
                txtCardCount.text = match.matchRedCount
                txtScore.text = match.matchScore
                viewLine.setVisibilityOnCondition(isLastItem)

                val favoriteResource =
                    if (match.isFavorite) R.drawable.ic_star_fill else R.drawable.ic_star_outline
                icStar.setImageResource(favoriteResource)

                icStar.vibrateOnClick {
                    onMatchClickListener?.invoke(MatchEventListener.OnFavoritesClicked(matchId = match.matchId))
                }

                root.vibrateOnClick {
                    onMatchClickListener?.invoke(MatchEventListener.OnMatchClicked(match = match))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder =
        CardViewHolder(
            parent.inflateBinding(ItemMatchBinding::inflate) as ItemMatchBinding
        )

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) =
        holder.bind(match = getItem(position), isLastItem = position == itemCount - 1)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MatchesItemUIModel>() {
            override fun areItemsTheSame(
                oldItem: MatchesItemUIModel, newItem: MatchesItemUIModel
            ): Boolean {
                return oldItem.matchId == newItem.matchId
            }

            override fun areContentsTheSame(
                oldItem: MatchesItemUIModel, newItem: MatchesItemUIModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
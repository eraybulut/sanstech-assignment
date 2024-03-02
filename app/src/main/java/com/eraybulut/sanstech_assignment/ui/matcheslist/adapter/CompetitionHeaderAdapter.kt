package com.eraybulut.sanstech_assignment.ui.matcheslist.adapter

/**
 * Created by Eray BULUT on 2.03.2024
 * eraybulutlar@gmail.com
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eraybulut.sanstech_assignment.databinding.ItemCompetitionHeaderBinding
import javax.inject.Inject

class CompetitionHeaderAdapter @Inject constructor() :
    RecyclerView.Adapter<CompetitionHeaderAdapter.CardViewHolder>() {

    inner class CardViewHolder(private val binding: ItemCompetitionHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            with(binding) {
                val adapter = MatchesAdapter()
                binding.rvMatch.adapter = adapter
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder =
        CardViewHolder(
            ItemCompetitionHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = 10
}
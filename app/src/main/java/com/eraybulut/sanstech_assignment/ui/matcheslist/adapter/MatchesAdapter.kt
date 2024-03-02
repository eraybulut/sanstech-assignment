package com.eraybulut.sanstech_assignment.ui.matcheslist.adapter

/**
 * Created by Eray BULUT on 2.03.2024
 * eraybulutlar@gmail.com
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eraybulut.sanstech_assignment.databinding.ItemMatchBinding

class MatchesAdapter : RecyclerView.Adapter<MatchesAdapter.CardViewHolder>() {

    init {
        setHasStableIds(true)
    }

    inner class CardViewHolder(private val binding: ItemMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder =
        CardViewHolder(
            ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = 10
}
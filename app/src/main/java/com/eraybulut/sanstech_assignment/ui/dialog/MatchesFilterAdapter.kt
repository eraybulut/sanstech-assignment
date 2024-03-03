package com.eraybulut.sanstech_assignment.ui.dialog

/**
 * Created by Eray BULUT on 3.03.2024
 * eraybulutlar@gmail.com
 */

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eraybulut.sanstech_assignment.databinding.ItemMatchesFilterBinding
import com.eraybulut.sanstech_assignment.utils.enums.MatchStatusTypes
import com.eraybulut.sanstech_assignment.utils.extensions.vibrateOnClick

class MatchesFilterAdapter : RecyclerView.Adapter<MatchesFilterAdapter.CardViewHolder>() {

    private var filterTypes = emptyList<MatchesFilterItemModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setFilterTypes(newFilterTypes: List<MatchesFilterItemModel>) {
        filterTypes = newFilterTypes
        notifyDataSetChanged()
    }

    private var onFilterClickListener: ((MatchStatusTypes) -> Unit)? = null

    fun setOnFilterClickListener(listener: (MatchStatusTypes) -> Unit) {
        onFilterClickListener = listener
    }

    inner class CardViewHolder(private val binding: ItemMatchesFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(filter: MatchesFilterItemModel) {
            with(binding) {
                txtFilterName.text = filter.filterName

                root.vibrateOnClick {
                    onFilterClickListener?.invoke(filter.filterMatchStatus)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder =
        CardViewHolder(
            ItemMatchesFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) =
        holder.bind(filter = filterTypes[position])

    override fun getItemCount(): Int = filterTypes.size
}
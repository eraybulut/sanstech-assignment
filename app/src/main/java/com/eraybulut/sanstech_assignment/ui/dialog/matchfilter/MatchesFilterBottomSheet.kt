package com.eraybulut.sanstech_assignment.ui.dialog.matchfilter

import com.eraybulut.sanstech_assignment.R
import com.eraybulut.sanstech_assignment.base.BaseBottomSheet
import com.eraybulut.sanstech_assignment.databinding.DialogMatchFilterBinding
import com.eraybulut.sanstech_assignment.utils.enums.MatchStatusTypes

/**
 * Created by Eray BULUT on 3.03.2024
 * eraybulutlar@gmail.com
 */

class MatchesFilterBottomSheet : BaseBottomSheet<DialogMatchFilterBinding>(
    DialogMatchFilterBinding::inflate
) {
    private val filterAdapter: MatchesFilterAdapter by lazy {
        MatchesFilterAdapter()
    }

    private var onFilterClickListener: ((MatchStatusTypes) -> Unit)? = null

    fun setOnFilterClickListener(listener: (MatchStatusTypes) -> Unit) {
        onFilterClickListener = listener
    }

    override fun onCreateFinished() {
        setupFilterRecyclerView()
    }

    private fun setupFilterRecyclerView() {
        with(binding.rvFilterMatch) {
            adapter = filterAdapter
            filterAdapter.apply {
                setFilterTypes(newFilterTypes = getFilterTypes())
                setOnFilterClickListener {
                    onFilterClickListener?.invoke(it)
                    dismiss()
                }
            }
        }
    }

    private fun getFilterTypes(): List<MatchesFilterItemModel> {
        return listOf(
            MatchesFilterItemModel(
                filterName = getString(R.string.match_status_all),
                filterMatchStatus = MatchStatusTypes.ALL
            ), MatchesFilterItemModel(
                filterName = getString(R.string.match_status_schedule),
                filterMatchStatus = MatchStatusTypes.SCHEDULE
            ), MatchesFilterItemModel(
                filterName = getString(R.string.match_status_second_quarter),
                filterMatchStatus = MatchStatusTypes.SECOND_QUARTER
            ), MatchesFilterItemModel(
                filterName = getString(R.string.match_status_half_time),
                filterMatchStatus = MatchStatusTypes.HALF_TIME
            ), MatchesFilterItemModel(
                filterName = getString(R.string.match_status_finished),
                filterMatchStatus = MatchStatusTypes.FINISHED
            ), MatchesFilterItemModel(
                filterName = getString(R.string.match_status_deferred),
                filterMatchStatus = MatchStatusTypes.DEFERRED
            )
        )
    }
}
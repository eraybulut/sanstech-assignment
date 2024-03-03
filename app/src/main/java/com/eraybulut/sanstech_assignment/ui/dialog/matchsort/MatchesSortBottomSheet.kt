package com.eraybulut.sanstech_assignment.ui.dialog.matchsort

import com.eraybulut.sanstech_assignment.base.BaseBottomSheet
import com.eraybulut.sanstech_assignment.databinding.DialogMatchSortBinding
import com.eraybulut.sanstech_assignment.utils.enums.SortOrderTypes
import com.eraybulut.sanstech_assignment.utils.extensions.vibrateOnClick

/**
 * Created by Eray BULUT on 3.03.2024
 * eraybulutlar@gmail.com
 */

class MatchesSortBottomSheet : BaseBottomSheet<DialogMatchSortBinding>(
    DialogMatchSortBinding::inflate
) {

    private var onSortClickListener: ((SortOrderTypes) -> Unit)? = null

    fun setOnSortClickListener(listener: ((SortOrderTypes) -> Unit)) {
        onSortClickListener = listener
    }

    override fun initializeListeners() {
        with(binding) {

            txtSortOld.vibrateOnClick {
                onSortClickListener?.invoke(SortOrderTypes.OLDEST_FIRST)
                dismiss()
            }

            txtSortNew.vibrateOnClick {
                onSortClickListener?.invoke(SortOrderTypes.NEWEST_FIRST)
                dismiss()
            }
        }
    }
}
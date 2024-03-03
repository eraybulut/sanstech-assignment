package com.eraybulut.sanstech_assignment.ui.dialog.matchfilter

import com.eraybulut.sanstech_assignment.utils.Constants.EMPTY_STRING
import com.eraybulut.sanstech_assignment.utils.enums.MatchStatusTypes

/**
 * Created by Eray BULUT on 3.03.2024
 * eraybulutlar@gmail.com
 */

data class MatchesFilterItemModel(
    val filterName: String = EMPTY_STRING,
    val filterMatchStatus: MatchStatusTypes = MatchStatusTypes.ALL
)
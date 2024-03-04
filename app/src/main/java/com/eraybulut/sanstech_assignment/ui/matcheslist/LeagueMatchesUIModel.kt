package com.eraybulut.sanstech_assignment.ui.matcheslist

import com.eraybulut.sanstech_assignment.utils.Constants.EMPTY_STRING
import com.eraybulut.sanstech_assignment.utils.Constants.ZERO

/**
 * Created by Eray BULUT on 2.03.2024
 * eraybulutlar@gmail.com
 */

data class LeagueMatchesUIModel(
    val leagueName: String = EMPTY_STRING,
    val leagueId: Int = ZERO,
    val flag: String = EMPTY_STRING,
    val matches: List<MatchesItemUIModel> = emptyList(),
)


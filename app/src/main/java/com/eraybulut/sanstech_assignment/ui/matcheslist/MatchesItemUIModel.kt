package com.eraybulut.sanstech_assignment.ui.matcheslist

import android.os.Parcelable
import com.eraybulut.sanstech_assignment.utils.Constants.EMPTY_STRING
import com.eraybulut.sanstech_assignment.utils.Constants.ZERO
import kotlinx.parcelize.Parcelize

/**
 * Created by Eray BULUT on 2.03.2024
 * eraybulutlar@gmail.com
 */
@Parcelize
data class MatchesItemUIModel(
    val matchId: Int = ZERO,
    val matchTime: Long = 0L,
    val matchAbbr: String = EMPTY_STRING,
    val matchRedCount: String = EMPTY_STRING,
    val matchScore: String = EMPTY_STRING,
    val homeTeamName: String = EMPTY_STRING,
    val awayTeamName: String = EMPTY_STRING,
    val matchStatus: Int = ZERO,
    val isFavorite: Boolean = false
): Parcelable
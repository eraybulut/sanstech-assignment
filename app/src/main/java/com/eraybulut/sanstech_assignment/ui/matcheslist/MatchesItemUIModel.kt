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
    var matchId: Int = ZERO,
    var matchTime: Long = 0L,
    var matchAbbr: String = EMPTY_STRING,
    var matchRedCount: String = EMPTY_STRING,
    var matchScore: String = EMPTY_STRING,
    var homeTeamName: String = EMPTY_STRING,
    var awayTeamName: String = EMPTY_STRING,
    var matchStatus: Int = ZERO,
    var isFavorite: Boolean = false
): Parcelable
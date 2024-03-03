package com.eraybulut.sanstech_assignment.ui.matcheslist

/**
 * Created by Eray BULUT on 3.03.2024
 * eraybulutlar@gmail.com
 */

sealed interface MatchEventListener {
    data class OnMatchClicked(val match: MatchesItemUIModel) : MatchEventListener

    data class OnFavoritesClicked(val matchId: Int) : MatchEventListener
}
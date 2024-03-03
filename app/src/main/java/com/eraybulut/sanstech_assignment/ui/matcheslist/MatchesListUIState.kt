package com.eraybulut.sanstech_assignment.ui.matcheslist

/**
 * Created by Eray BULUT on 2.03.2024
 * eraybulutlar@gmail.com
 */

sealed interface MatchesListUIState {
    data object Idle : MatchesListUIState
    data object Empty : MatchesListUIState
    data class Success(val data: List<LeagueMatchesUIModel>) : MatchesListUIState
}
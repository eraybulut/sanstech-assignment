package com.eraybulut.sanstech_assignment.ui.matcheslist

import androidx.lifecycle.viewModelScope
import com.eraybulut.sanstech_assignment.base.BaseViewModel
import com.eraybulut.sanstech_assignment.data.ResultWrapper
import com.eraybulut.sanstech_assignment.domain.usecase.GetMatchesUseCase
import com.eraybulut.sanstech_assignment.utils.enums.MatchStatusTypes
import com.eraybulut.sanstech_assignment.utils.enums.SortOrderTypes
import com.eraybulut.sanstech_assignment.utils.extensions.orZero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Eray BULUT on 2.03.2024
 * eraybulutlar@gmail.com
 */

@HiltViewModel
class MatchesListViewModel @Inject constructor(
    private val getMatchesUseCase: GetMatchesUseCase
) : BaseViewModel() {

    private val _matchesListUIState = MutableStateFlow<MatchesListUIState>(MatchesListUIState.Idle)
    val matchesListUIState get() = _matchesListUIState.asStateFlow()

    private val _isRefreshVisible = Channel<Boolean>()
    val isRefreshVisible get() = _isRefreshVisible.receiveAsFlow()

    private var matches = mutableListOf<LeagueMatchesUIModel>()

    fun getMatches() {
        showLoading()
        matches.clear()
        viewModelScope.launch {
            getMatchesUseCase().collect { result ->
                when (result) {
                    is ResultWrapper.Success -> {
                        hideLoading()
                        _isRefreshVisible.trySend(false)
                        val mappedMatches = result.value
                        matches.addAll(mappedMatches)
                        _matchesListUIState.value = if (mappedMatches.isEmpty()) {
                            MatchesListUIState.Empty
                        } else {
                            MatchesListUIState.Success(mappedMatches)
                        }
                    }

                    is ResultWrapper.Error -> {
                        hideLoading()
                        showToast(message = result.message)
                    }
                }
            }
        }
    }

    fun refreshMatchesList() {
        _isRefreshVisible.trySend(true)
        getMatches()
    }

    fun toggleFavoriteStatus(matchId: Int) {
        _matchesListUIState.update { matchesUIState ->
            when (matchesUIState) {
                is MatchesListUIState.Success -> {
                    val updatedList = matchesUIState.data.map { leagueMatchesUIModel ->
                        leagueMatchesUIModel.copy(matches = leagueMatchesUIModel.matches.map { match ->
                            if (match.matchId == matchId) {
                                match.copy(isFavorite = !match.isFavorite)
                            } else {
                                match
                            }
                        })
                    }
                    MatchesListUIState.Success(updatedList)
                }

                else -> matchesUIState
            }
        }
    }

    fun filterMatchesByStatus(status: MatchStatusTypes) {
        val filteredMatches = when (status) {
            MatchStatusTypes.ALL -> matches
            else -> matches.mapNotNull { leagueMatchesUIModel ->
                leagueMatchesUIModel.takeIf { it.matches.any { match -> match.matchStatus == status.value } }
                    ?.copy(matches = leagueMatchesUIModel.matches.filter { match ->
                        match.matchStatus == status.value
                    })
            }
        }
        _matchesListUIState.value = if (filteredMatches.isEmpty()) {
            MatchesListUIState.Empty
        } else {
            MatchesListUIState.Success(filteredMatches)
        }
    }

    fun sortMatches(sortOrder: SortOrderTypes) {
        val sortedMatches = when (sortOrder) {
            SortOrderTypes.NEWEST_FIRST -> matches.map { leagueMatchesUIModel ->
                leagueMatchesUIModel.copy(matches = leagueMatchesUIModel.matches.sortedByDescending { it.matchTime })
            }.sortedByDescending { it.matches.firstOrNull()?.matchTime.orZero() }


            SortOrderTypes.OLDEST_FIRST -> matches.map { leagueMatchesUIModel ->
                leagueMatchesUIModel.copy(matches = leagueMatchesUIModel.matches.sortedBy { it.matchTime })
            }.sortedBy { it.matches.firstOrNull()?.matchTime.orZero() }
        }
        _matchesListUIState.value = MatchesListUIState.Success(sortedMatches)
    }
}
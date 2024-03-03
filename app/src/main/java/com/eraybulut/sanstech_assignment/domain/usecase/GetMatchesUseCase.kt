package com.eraybulut.sanstech_assignment.domain.usecase

import com.eraybulut.sanstech_assignment.data.ResultWrapper
import com.eraybulut.sanstech_assignment.data.repository.MatchRepository
import com.eraybulut.sanstech_assignment.domain.mapper.toLeagueMatchesUIModel
import com.eraybulut.sanstech_assignment.ui.matcheslist.LeagueMatchesUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Eray BULUT on 2.03.2024
 * eraybulutlar@gmail.com
 */


class GetMatchesUseCase @Inject constructor(
    private val matchRepository: MatchRepository
) {
    operator fun invoke(): Flow<ResultWrapper<List<LeagueMatchesUIModel>>> = flow {
        val response = matchRepository.getMatches()
        if (response.success == true) {
            emit(ResultWrapper.Success(response.data?.toLeagueMatchesUIModel().orEmpty()))
        } else {
            emit(ResultWrapper.Error(response.error.orEmpty()))
        }
    }.catch { e ->
        emit(ResultWrapper.Error(e.message.orEmpty()))
    }
}


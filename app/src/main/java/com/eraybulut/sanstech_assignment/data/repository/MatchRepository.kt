package com.eraybulut.sanstech_assignment.data.repository

import com.eraybulut.sanstech_assignment.data.model.request.BaseResponse
import com.eraybulut.sanstech_assignment.data.model.response.MatchesResponseModel
import com.eraybulut.sanstech_assignment.data.service.MatchService
import javax.inject.Inject

/**
 * Created by Eray BULUT on 2.03.2024
 * eraybulutlar@gmail.com
 */

class MatchRepository @Inject constructor(
    private val matchService: MatchService
) {
    suspend fun getMatches() : BaseResponse<List<MatchesResponseModel>>{
        return matchService.getMatches()
    }
}
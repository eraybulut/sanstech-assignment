package com.eraybulut.sanstech_assignment.domain.service

import com.eraybulut.sanstech_assignment.data.model.request.BaseResponse
import com.eraybulut.sanstech_assignment.data.model.response.MatchesResponseModel
import retrofit2.http.GET

/**
 * Created by Eray BULUT on 1.03.2024
 * eraybulutlar@gmail.com
 */

interface ApiService {

    @GET("statistics/sport/SOCCER/matches")
    suspend fun getMatches(): BaseResponse<List<MatchesResponseModel>>
}
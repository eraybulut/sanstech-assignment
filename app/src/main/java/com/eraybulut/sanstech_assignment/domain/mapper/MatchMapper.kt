package com.eraybulut.sanstech_assignment.domain.mapper

import com.eraybulut.sanstech_assignment.data.model.response.MatchesResponseModel
import com.eraybulut.sanstech_assignment.ui.matcheslist.LeagueMatchesUIModel
import com.eraybulut.sanstech_assignment.ui.matcheslist.MatchesItemUIModel
import com.eraybulut.sanstech_assignment.utils.extensions.orZero

/**
 * Created by Eray BULUT on 2.03.2024
 * eraybulutlar@gmail.com
 */


fun List<MatchesResponseModel>.toLeagueMatchesUIModel(): List<LeagueMatchesUIModel> {
    return this.groupBy { it.to?.i.orZero() }
        .map { (leagueId, matches) ->
            LeagueMatchesUIModel(
                leagueName = matches.firstOrNull()?.to?.n.orEmpty(),
                leagueId = leagueId,
                flag = matches.firstOrNull()?.to?.flag.orEmpty(),
                matches = matches.map { responseModel ->
                    MatchesItemUIModel(
                        matchId = responseModel.i.orZero(),
                        matchTime = responseModel.d.orZero(),
                        matchAbbr = responseModel.sc?.abbr.orEmpty(),
                        homeTeamName = responseModel.ht?.n.orEmpty(),
                        matchRedCount = "${responseModel.ht?.rc.orZero()} - ${responseModel.at?.rc.orZero()}",
                        matchScore = "${responseModel.sc?.ht?.r.orZero()} - ${responseModel.sc?.at?.r.orZero()}",
                        awayTeamName = responseModel.at?.n.orEmpty(),
                        matchStatus = responseModel.sc?.st.orZero()
                    )
                }
            )
        }
}




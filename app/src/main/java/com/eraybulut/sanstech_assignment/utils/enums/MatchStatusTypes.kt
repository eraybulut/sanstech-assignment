package com.eraybulut.sanstech_assignment.utils.enums

/**
 * Created by Eray BULUT on 3.03.2024
 * eraybulutlar@gmail.com
 */

enum class MatchStatusTypes(var value: Int) {
    ALL(0),
    SCHEDULE(1),
    SECOND_QUARTER(3),
    HALF_TIME(63),
    FINISHED(5),
    DEFERRED(24)
}
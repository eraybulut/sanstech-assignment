package com.eraybulut.sanstech_assignment.domain

/**
 * Created by Eray BULUT on 1.03.2024
 * eraybulutlar@gmail.com
 */

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(val message: String) : ResultWrapper<Nothing>()
}

fun <T> ResultWrapper<T>.onSuccess(action: (T) -> Unit): ResultWrapper<T> {
    return when (this) {
        is ResultWrapper.Success -> {
            action.invoke(value)
            this
        }

        else -> this
    }
}


fun <T> ResultWrapper<T>.onError(action: (String) -> Unit): ResultWrapper<T> {
    return when (this) {
        is ResultWrapper.Error -> {
            action.invoke(message)
            this
        }

        else -> this
    }
}
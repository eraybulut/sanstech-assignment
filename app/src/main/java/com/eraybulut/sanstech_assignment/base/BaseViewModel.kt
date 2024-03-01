package com.eraybulut.sanstech_assignment.base

import androidx.lifecycle.ViewModel
import com.eraybulut.sanstech_assignment.data.model.request.BaseResponse
import com.eraybulut.sanstech_assignment.domain.ResultWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.net.SocketTimeoutException

/**
 * Created by Eray BULUT on 1.03.2024
 * eraybulutlar@gmail.com
 */

abstract class BaseViewModel : ViewModel() {


    private val _loading = MutableStateFlow(false)
    val loading get() = _loading.asStateFlow()

    fun showLoading() {
        _loading.value = true
    }

    fun hideLoading() {
        _loading.value = false
    }


    suspend fun <T> safeApiCall(apiCall: suspend () -> BaseResponse<T>): ResultWrapper<T?> {
        return try {
            val response = apiCall.invoke()
            if (response.success) {
                ResultWrapper.Success(response.data)
            } else {
                ResultWrapper.Error(response.error.orEmpty())
            }
        } catch (throwable: Throwable) {
            handleApiError(throwable)
        }
    }

    private fun <T> handleApiError(throwable: Throwable): ResultWrapper<T> {
        return when (throwable) {

            is SocketTimeoutException -> {
                ResultWrapper.Error("Connection Timed Out")
            }

            else -> {
                ResultWrapper.Error("A Problem Occurred")
            }
        }
    }
}
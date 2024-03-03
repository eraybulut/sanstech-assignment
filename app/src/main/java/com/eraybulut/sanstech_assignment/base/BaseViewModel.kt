package com.eraybulut.sanstech_assignment.base

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eraybulut.sanstech_assignment.data.model.request.BaseResponse
import com.eraybulut.sanstech_assignment.data.ResultWrapper
import com.eraybulut.sanstech_assignment.utils.extensions.orFalse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException

/**
 * Created by Eray BULUT on 1.03.2024
 * eraybulutlar@gmail.com
 */

abstract class BaseViewModel : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading get() = _loading.asStateFlow()

    private val _baseEvent = MutableSharedFlow<BaseViewEvent>()
    val baseEvent: SharedFlow<BaseViewEvent> get() = _baseEvent.asSharedFlow()

    fun showLoading() {
        _loading.value = true
    }

    fun hideLoading() {
        _loading.value = false
    }

    fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
        sendEvent(BaseViewEvent.ShowToast(message, duration))

    fun showToast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) =
        sendEvent(BaseViewEvent.ShowToast(message, duration))

    private fun sendEvent(event: BaseViewEvent) {
        viewModelScope.launch {
            _baseEvent.emit(event)
        }
    }

    suspend fun <T> safeApiCall(apiCall: suspend () -> BaseResponse<T>): ResultWrapper<T?> {
        return try {
            val response = apiCall.invoke()
            if (response.success.orFalse()) {
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
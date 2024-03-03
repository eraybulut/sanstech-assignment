package com.eraybulut.sanstech_assignment.data.model.request

import com.google.gson.annotations.SerializedName

/**
 * Created by Eray BULUT on 1.03.2024
 * eraybulutlar@gmail.com
 */

data class BaseResponse<T>(
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("data")
    val data: T? = null,
    @SerializedName("error")
    val error: String? = null,
)
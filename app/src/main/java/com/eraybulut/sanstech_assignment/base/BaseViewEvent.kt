package com.eraybulut.sanstech_assignment.base

import android.widget.Toast

/**
 * Created by Eray BULUT on 3.03.2024
 * eraybulutlar@gmail.com
 */

sealed class BaseViewEvent {
    data class ShowToast<T>(val message: T, val duration: Int = Toast.LENGTH_SHORT) :
        BaseViewEvent()

}
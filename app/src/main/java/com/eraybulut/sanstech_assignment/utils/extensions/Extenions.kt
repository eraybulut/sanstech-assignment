package com.eraybulut.sanstech_assignment.utils.extensions

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.eraybulut.sanstech_assignment.data.ResultWrapper
import com.eraybulut.sanstech_assignment.data.model.request.BaseResponse
import com.eraybulut.sanstech_assignment.utils.Constants.DATE_FORMAT
import com.eraybulut.sanstech_assignment.utils.Constants.ZERO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by Eray BULUT on 2.03.2024
 * eraybulutlar@gmail.com
 */

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

fun View.applyWindowInsets() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
    }
}

fun Long.toDateTimeString(): String {
    val date = Date(this)
    val format = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    return format.format(date)
}

fun ViewGroup.inflateBinding(inflateMethod: (LayoutInflater, ViewGroup, Boolean) -> ViewBinding, attachToRoot: Boolean = false): ViewBinding {
    return inflateMethod(LayoutInflater.from(context), this, attachToRoot)
}

fun RecyclerView.goStartPosition() {
    scrollToPosition(ZERO)
}

fun RecyclerView.goStartPositionSmooth() {
    smoothScrollToPosition(ZERO)
}
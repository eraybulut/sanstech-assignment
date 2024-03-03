package com.eraybulut.sanstech_assignment.utils.extensions

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.widget.Toast
import com.eraybulut.sanstech_assignment.R

/**
 * Created by Eray BULUT on 3.03.2024
 * eraybulutlar@gmail.com
 */

fun Context.vibrate(duration: Long = 50) {
    val vibrator = getSystemService(Vibrator::class.java)
    if (vibrator != null) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(
                VibrationEffect.createOneShot(
                    duration, VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(duration)
        }
    }
}


fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Context.shareText(message: String) {
    try {
        Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }.also { intent ->
            startActivity(intent)
        }
    } catch (e: Exception) {
        Log.d("ERROR","SHARE TEXT EXT ERROR",e)
    }
}
fun Context.copyToClipboard(text: String) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
    val clip = android.content.ClipData.newPlainText("Copied Text", text)
    clipboard.setPrimaryClip(clip)
}

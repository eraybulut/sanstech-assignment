package com.eraybulut.sanstech_assignment.utils.extensions

import android.view.View

/**
 * Created by Eray BULUT on 3.03.2024
 * eraybulutlar@gmail.com
 */

fun View.fadeIn(duration: Long = 300) {
    visible()
    this.animate().alpha(1f).setDuration(duration).start()
}

fun View.fadeOut(duration: Long = 300) {
    this.animate().alpha(0f).setDuration(duration).withEndAction {
        gone()
    }.start()
}
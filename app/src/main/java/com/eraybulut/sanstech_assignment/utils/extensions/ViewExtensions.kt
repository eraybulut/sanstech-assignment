package com.eraybulut.sanstech_assignment.utils.extensions

import android.view.View

/**
 * Created by Eray BULUT on 2.03.2024
 * eraybulutlar@gmail.com
 */

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.setVisibilityOnCondition(condition: Boolean) {
    if (condition) invisible() else visible()
}

fun View.vibrateOnClick(duration: Long = 50, debounceTime: Long = 500, action: (View) -> Unit) {
    var lastClickTime = 0L

    setOnClickListener { view ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime >= debounceTime) {
            context.vibrate(duration)
            action(view)
            lastClickTime = currentTime
        }
    }
}

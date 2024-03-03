package com.eraybulut.sanstech_assignment.utils.extensions

import android.widget.ImageView
import coil.load
import com.eraybulut.sanstech_assignment.R

/**
 * Created by Eray BULUT on 2.03.2024
 * eraybulutlar@gmail.com
 */

fun ImageView.loadUrl(url: String?) {
    load(url) {
        crossfade(true)
        crossfade(500)
        placeholder(R.drawable.ic_flag_circle)
        error(R.drawable.ic_error)
    }
}

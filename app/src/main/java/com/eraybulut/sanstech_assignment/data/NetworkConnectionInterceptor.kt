package com.eraybulut.sanstech_assignment.data

import android.content.Context
import com.eraybulut.sanstech_assignment.utils.NetworkUtils.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by Eray BULUT on 1.03.2024
 * eraybulutlar@gmail.com
 */

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (isNetworkAvailable(context).not()) {
            throw NoConnectionException()
        }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    inner class NoConnectionException : IOException() {
        override val message: String
            get() = super.message ?: "No Internet Connection"
    }
}
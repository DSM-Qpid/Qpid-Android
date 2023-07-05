package com.example.qpid_android.api

import android.util.Log
import com.example.qpid_android.MainActivity.Companion.token
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.encodedPath
        val method = request.method

        val ignorePath = listOf(
            "/user/login",
            "/user/signup"
        )

        return if (ignorePath.contains(path))
            chain.proceed(
                request.newBuilder().header("ngrok-skip-browser-warning", "69420").build()
            )
        else {
            Log.d("TAG", "intercept: $token")
            chain.proceed(
                request.newBuilder()
                    .header("Authorization", token)
                    .header("ngrok-skip-browser-warning", "69420").build()
            )
        }

    }
}
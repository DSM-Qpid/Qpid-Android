package com.example.qpid_android.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val baseURI = "https://1ce7-119-203-74-86.ngrok-free.app/"

    private val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val authorizationInterceptor = AuthorizationInterceptor()
    private val emptyBodyInterceptor = EmptyBodyInterceptor()

    val retrofit = Retrofit.Builder()
        .baseUrl(baseURI)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(authorizationInterceptor)
                .addInterceptor(emptyBodyInterceptor)
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getAPI(): QpidAPI {
        return retrofit.create(QpidAPI::class.java)
    }
}

private fun okHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val authorizationInterceptor = AuthorizationInterceptor()
    val emptyBodyInterceptor = EmptyBodyInterceptor()

    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        //.addInterceptor(authorizationInterceptor)
        .addInterceptor(emptyBodyInterceptor)
        .build()
}
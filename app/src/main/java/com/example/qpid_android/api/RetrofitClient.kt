package com.example.qpid_android.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val baseURI = "http://172.20.67.156:5436/"

    val retrofit = Retrofit.Builder()
        .baseUrl(baseURI)
        .client(okHttpClient())
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
        .addInterceptor(authorizationInterceptor)
        .addInterceptor(emptyBodyInterceptor)
        .build()
}
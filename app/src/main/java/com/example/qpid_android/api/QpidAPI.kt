package com.example.qpid_android.api

import com.example.qpid_android.api.feed.FeedRequest
import com.example.qpid_android.api.profile.ProfileResponse
import com.example.qpid_android.api.signin.SigninRequest
import com.example.qpid_android.api.signup.SignupRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface QpidAPI {

    @POST("users/signup")
    suspend fun signup(
        @Body signupRequest: SignupRequest,
    )

    @POST("users/login")
    suspend fun signin(
        @Body signinRequest: SigninRequest,
    )

    @GET("users/info")
    suspend fun getProfile(): ProfileResponse

    @POST("users/refresh")
    suspend fun tokenRefresh()

    @POST("feeds")
    suspend fun createFeed(
        @Body feedRequest: FeedRequest,
    )

    @PATCH("{feed-id}")
    suspend fun patchFeed(
        @Path("feed-id") id: Int,
        @Body feedRequest: FeedRequest,
    )

}

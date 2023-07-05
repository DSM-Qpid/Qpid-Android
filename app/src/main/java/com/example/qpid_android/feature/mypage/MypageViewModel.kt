package com.example.qpid_android.feature.mypage

import com.example.qpid_android.api.RetrofitClient
import com.example.qpid_android.api.profile.ProfileResponse
import com.example.qpid_android.base.BaseViewModel
import com.example.qpid_android.feature.signin.SigninViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MypageViewModel @Inject constructor(): BaseViewModel<MypageViewModel.Event>() {

    fun getProfile() {

        val retrofitClient = RetrofitClient()

        execute(
            job = { retrofitClient.getAPI().getProfile() },
            onSuccess = { emitEvent(Event.Success(it)) },
            onFailure = {  }
        )
    }

    sealed class Event {
        data class Success(val data: ProfileResponse) : Event()
    }
}
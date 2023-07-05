package com.example.qpid_android.feature.signup

import com.example.qpid_android.api.RetrofitClient
import com.example.qpid_android.api.signup.SignupRequest
import com.example.qpid_android.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(): BaseViewModel<SignupViewModel.Event>() {

    fun signup(
        id: String,
        password: String,
        name: String,
    ) {
        val retrofitClient = RetrofitClient()

        execute(
            job = { retrofitClient.getAPI().signup(SignupRequest(id, password, name)) },
            onSuccess = { emitEvent(Event.Success) },
            onFailure = { emitEvent(Event.Fail("입력 형식이 알맞지 않습니다.")) },
        )
    }
    sealed class Event {
        object Success : Event()
        data class Fail(val message: String) : Event()
    }
}

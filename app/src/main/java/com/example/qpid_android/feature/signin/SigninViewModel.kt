package com.example.qpid_android.feature.signin

import com.example.qpid_android.MainActivity.Companion.token
import com.example.qpid_android.api.RetrofitClient
import com.example.qpid_android.api.signin.SigninRequest
import com.example.qpid_android.base.BaseViewModel
import com.example.qpid_android.feature.signup.SignupViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SigninViewModel @Inject constructor() : BaseViewModel<SigninViewModel.Event>() {

    fun signin(
        id: String,
        password: String,
    ) {
        val retrofitClient = RetrofitClient()

        execute(
            job = { retrofitClient.getAPI().signin(SigninRequest(id, password)) },
            onSuccess = {
                token = "Bearer " + it.access_token
                emitEvent(Event.Success)
            },
            onFailure = { emitEvent(Event.Fail(it.message ?: "")) },
        )
    }

    sealed class Event {
        object Success : Event()
        data class Fail(val message: String) : Event()
    }
}
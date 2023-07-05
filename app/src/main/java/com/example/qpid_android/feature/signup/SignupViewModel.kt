package com.example.qpid_android.feature.signup

import androidx.lifecycle.viewModelScope
import com.example.qpid_android.api.RetrofitClient
import com.example.qpid_android.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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
            job = {
                  emitEvent(Event.Success)
                // retrofitClient.getAPI().signup(SignupRequest(id, password, name))
            },
            onSuccess = { emitEvent(Event.Success) },
            onFailure = { emitEvent(Event.Fail(it.message ?: "")) },
        )
    }
    sealed class Event {
        object Success : Event()
        data class Fail(val message: String) : Event()
    }
}

package com.example.qpid_android.feature.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qpid_android.api.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(): ViewModel() {

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> get() = _success

    private val _fail = MutableLiveData<String>()
    val fail: LiveData<String> get() = _fail

    fun signup(
        id: String,
        password: String,
        name: String,
    ) {
        val retrofitClient = RetrofitClient()

        viewModelScope.launch {
            kotlin.runCatching {
                _success.value = true
                // retrofitClient.getAPI().signup(SignupRequest(id, password, name))
            }.onSuccess {
                _success.value = true
            }.onFailure {
                _fail.value = it.message
            }
        }
    }

}
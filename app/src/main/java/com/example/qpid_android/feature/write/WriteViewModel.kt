package com.example.qpid_android.feature.write

import com.example.qpid_android.api.RetrofitClient
import com.example.qpid_android.api.feed.FeedRequest
import com.example.qpid_android.base.BaseViewModel
import com.example.qpid_android.feature.mypage.MypageViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor(): BaseViewModel<WriteViewModel.Event>() {

    private val retrofitClient = RetrofitClient()

    fun writeFeed(
        title: String,
        content: String,
        tag: String
    ) {
        execute(
            job = { retrofitClient.getAPI().createFeed(FeedRequest(title, content, tag)) },
            onSuccess = { emitEvent(Event.Success) },
            onFailure = { emitEvent(Event.Fail("입력 형식이 알맞지 않습니다.")) },
        )
    }

    fun patchFeed(
        id: Int,
        title: String,
        content: String,
        tag: String
    ) {
        execute(
            job = {  retrofitClient.getAPI().patchFeed(id, FeedRequest(title, content, tag)) },
            onSuccess = { emitEvent(Event.Success) },
            onFailure = { emitEvent(Event.Fail("입력 형식이 알맞지 않습니다.")) },
        )
    }


    sealed class Event {
        object Success : Event()
        data class Fail(val message: String) : Event()
    }
}
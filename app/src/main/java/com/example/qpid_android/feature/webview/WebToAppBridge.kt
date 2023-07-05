package com.example.qpid_android.feature.webview

import android.webkit.JavascriptInterface
import com.google.gson.Gson

class WebToAppBridge(
    val goToWrite: () -> Unit = {},
    val giveToken: () -> (String) = {""},
    val goToPatch: (PatchInfo) -> Unit = {},
    private val gson: Gson = Gson(),
) {

    @JavascriptInterface
    fun write() = goToWrite()

    @JavascriptInterface
    fun token() = giveToken()

    @JavascriptInterface
    fun patch(data: String) =
        goToPatch(gson.fromJson(data, PatchInfo::class.java))
}
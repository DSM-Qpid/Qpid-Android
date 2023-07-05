package com.example.qpid_android.feature.webview

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.qpid_android.MainActivity.Companion.token
import com.example.qpid_android.design_system.typograpy.PreMedium16
import com.example.qpid_android.navigation.QpidNavigationItem
import com.example.qpid_android.util.updateUi

@Composable
fun WebViewScreen(
    navController: NavController,
    url: String,
) {
    val context = LocalContext.current
    val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = manager.activeNetwork

    var webView: WebView? by remember { mutableStateOf(null) }
    val bridge = WebToAppBridge(
        goToWrite = { updateUi { navController.navigate(QpidNavigationItem.Write.route) } },
        giveToken = { return@WebToAppBridge token },
        goToPatch = { info ->
            updateUi {
                navController.navigate(
                    route = QpidNavigationItem.Write.route +
                            "id${info.id}" +
                            "title${info.title}" +
                            "content${info.content}" +
                            "tag${info.tag}"
                )
            }
        }
    )

    if (networkInfo != null) {
        WebView(
            url = url,
            bridges = mapOf(Pair("webview", bridge)),
            onCreate = { webView = it }
        )
    } else {
        PreMedium16(
            text = "네트워크 에러",
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        )
    }
}

@SuppressLint("JavascriptInterface", "SetJavaScriptEnabled")
@Composable
fun WebView(
    modifier: Modifier = Modifier,
    url: String,
    bridges: Map<String, Any>,
    onCreate: (WebView) -> Unit
) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {

                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )

                webViewClient = object : WebViewClient() {}
                webChromeClient = WebChromeClient()
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(false)
                settings.builtInZoomControls = false
                settings.javaScriptEnabled = true
                settings.javaScriptCanOpenWindowsAutomatically = true
                settings.setSupportMultipleWindows(false)
                settings.domStorageEnabled = true
                settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                bridges.forEach { addJavascriptInterface(it.value, it.key) }
                loadUrl(url)
                onCreate(this)
            }
        },
        modifier = modifier,
    )
}

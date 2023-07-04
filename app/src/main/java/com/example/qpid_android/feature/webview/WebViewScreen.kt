package com.example.qpid_android.feature.webview

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.qpid_android.util.setBlueStatusBar
import com.example.qpid_android.util.setLightStatusBar

@Composable
fun WebViewScreen(
    navController: NavController,
    url: String,
) {

    val context = LocalContext.current
    context.setLightStatusBar()
}

package com.example.qpid_android.util

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import com.example.qpid_android.design_system.color.QpidColor
import com.example.qpid_android.getActivity

fun Activity.setStatusBarTransparent() {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
    if (Build.VERSION.SDK_INT >= 30)
        WindowCompat.setDecorFitsSystemWindows(window, false)
}


fun Context.getStatusBarHeightDp(): Int {
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resourceId > 0) resources.getDimensionPixelSize(resourceId).pixelToDp(this)
    else 0
}

fun Context.getNavigationBarHeightDp(): Int {
    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resourceId > 0) resources.getDimensionPixelSize(resourceId).pixelToDp(this)
    else 0
}

fun Context.setLightStatusBar() {
    getActivity()?.window?.statusBarColor = QpidColor.White.toArgb()
}

fun Context.setDarkStatusBar() {
    getActivity()?.window?.statusBarColor = QpidColor.Black.toArgb()
}

fun Context.setBlueStatusBar() {
    getActivity()?.window?.statusBarColor = QpidColor.Blue.toArgb()
}

fun Int.pixelToDp(context: Context): Int =
    (this / context.resources.displayMetrics.density).toInt()

object DevicePaddings {
    var statusBarHeightDp: Int = 0
    var navigationBarHeightDp: Int = 0
}
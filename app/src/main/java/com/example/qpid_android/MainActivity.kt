package com.example.qpid_android

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.qpid_android.feature.main.MainScreen
import com.example.qpid_android.feature.splash.SplashScreen
import com.example.qpid_android.navigation.QpidNavigationItem
import com.example.qpid_android.util.DevicePaddings
import com.example.qpid_android.util.getNavigationBarHeightDp
import com.example.qpid_android.util.getStatusBarHeightDp
import com.example.qpid_android.util.setStatusBarTransparent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DevicePaddings.statusBarHeightDp = getStatusBarHeightDp()
        DevicePaddings.navigationBarHeightDp = getNavigationBarHeightDp()
        super.onCreate(savedInstanceState)

        setContent {
            BaseApp()
        }
    }
}

@Composable
fun BaseApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = QpidNavigationItem.Splash.route) {
        composable(QpidNavigationItem.Splash.route) {
            SplashScreen(navController)
        }
        composable(QpidNavigationItem.Main.route) {
            MainScreen(navController)
        }
    }
}

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}
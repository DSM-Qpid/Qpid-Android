package com.example.qpid_android

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.qpid_android.feature.main.MainScreen
import com.example.qpid_android.feature.signin.SigninScreen
import com.example.qpid_android.feature.signup.SignupScreen
import com.example.qpid_android.feature.splash.SplashScreen
import com.example.qpid_android.feature.write.WriteScreen
import com.example.qpid_android.navigation.QpidNavigationItem
import com.example.qpid_android.util.DevicePaddings
import com.example.qpid_android.util.getNavigationBarHeightDp
import com.example.qpid_android.util.getStatusBarHeightDp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DevicePaddings.statusBarHeightDp = getStatusBarHeightDp()
        DevicePaddings.navigationBarHeightDp = getNavigationBarHeightDp()
        super.onCreate(savedInstanceState)

        setContent {
            BaseApp()
        }
    }

    companion object {
        var token = ""
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
        composable(
            route = QpidNavigationItem.Write.route +
                    "id{id}" + "title{title}" + "content{content}" + "tag{tag}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument(name = "title") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(name = "content") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(name = "tag") {
                    type = NavType.StringType
                    defaultValue = ""
                },
            )
        ) {
            val id = it.arguments?.getInt("id")
            val title = it.arguments?.getString("title") ?: ""
            val content = it.arguments?.getString("content") ?: ""
            val tag = it.arguments?.getString("tag") ?: ""

            WriteScreen(
                navController = navController,
                id = id,
                titleT = title,
                contentT = content,
                tagT = tag,
            )
        }
        composable(QpidNavigationItem.Signin.route) {
            SigninScreen(navController)
        }
        composable(QpidNavigationItem.Signup.route) {
            SignupScreen(navController = navController)
        }
    }
}

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}



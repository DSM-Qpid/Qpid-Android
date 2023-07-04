package com.example.qpid_android.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.qpid_android.design_system.color.QpidColor
import com.example.qpid_android.feature.mypage.MypageScreen
import com.example.qpid_android.feature.webview.WebViewScreen
import com.example.qpid_android.navigation.QpidBottomNavigation
import com.example.qpid_android.navigation.QpidBottomNavigationItem
import com.example.qpid_android.util.DevicePaddings
import com.example.qpid_android.util.setLightStatusBar

@Composable
fun MainScreen(
    mainController: NavController,
) {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()

    val context = LocalContext.current
    context.setLightStatusBar()

    Scaffold(
        modifier = Modifier
            .background(QpidColor.Gray200)
            .padding(
                bottom = DevicePaddings.navigationBarHeightDp.dp,
            ),
        scaffoldState = scaffoldState,
        isFloatingActionButtonDocked = true,
        bottomBar = {
            QpidBottomNavigation(
                navController = navController,
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = QpidBottomNavigationItem.Search.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(QpidBottomNavigationItem.Search.route) {
                WebViewScreen(
                    navController = mainController,
                    url = "",
                )
            }
            composable(QpidBottomNavigationItem.Board.route) {
                WebViewScreen(
                    navController = mainController,
                    url = ""
                )
            }
            composable(QpidBottomNavigationItem.Mypage.route) {
                MypageScreen(navController = mainController)
            }
        }
    }
}

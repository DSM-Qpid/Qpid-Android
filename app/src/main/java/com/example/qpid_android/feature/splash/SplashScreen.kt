package com.example.qpid_android.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.qpid_android.R
import com.example.qpid_android.design_system.color.QpidColor
import com.example.qpid_android.navigation.QpidNavigationItem
import com.example.qpid_android.util.setBlueStatusBar
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    context.setBlueStatusBar()

    LaunchedEffect(Unit) {
        delay(2000)

        navController.navigate(QpidNavigationItem.Main.route) {
            popUpTo(0)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = QpidColor.Blue)
            .wrapContentSize(align = Alignment.Center)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null
        )
    }

}
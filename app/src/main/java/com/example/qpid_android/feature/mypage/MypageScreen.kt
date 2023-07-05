package com.example.qpid_android.feature.mypage

import android.widget.ScrollView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.qpid_android.design_system.color.QpidColor
import com.example.qpid_android.util.setBlueStatusBar
import com.example.qpid_android.R
import com.example.qpid_android.design_system.component.Line
import com.example.qpid_android.design_system.typograpy.PreSemiBold24
import com.example.qpid_android.design_system.typograpy.PreSemiBold32
import com.example.qpid_android.feature.webview.WebViewScreen

@Composable
fun MypageScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    context.setBlueStatusBar()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = QpidColor.Blue),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row {
            PreSemiBold32(
                text = "햄스터",
                color = QpidColor.White,
            )
            PreSemiBold32(
                text = "님 안녕하세요!",
                color = QpidColor.White_Blue,
            )
        }
        
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = QpidColor.White,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Line(
                lineColor = QpidColor.Gray200,
                lineHeight = 15f,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
                    .width(120.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            
            PreSemiBold24(text = "내가 쓴 글")

            Spacer(modifier = Modifier.height(20.dp))

            WebViewScreen(navController = navController, url = "")
        }
    }
}
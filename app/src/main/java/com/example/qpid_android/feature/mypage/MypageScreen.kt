package com.example.qpid_android.feature.mypage

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.qpid_android.design_system.color.QpidColor
import com.example.qpid_android.R
import com.example.qpid_android.design_system.component.Line
import com.example.qpid_android.design_system.typograpy.PreSemiBold32
import com.example.qpid_android.feature.webview.WebViewScreen
import kotlinx.coroutines.flow.collect

@Composable
fun MypageScreen(
    navController: NavController,
    vm: MypageViewModel = hiltViewModel(),
) {
    val localDensity = LocalDensity.current
    var name by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        vm.getProfile()
    }

    LaunchedEffect(vm) {
        vm.eventFlow.collect {
            when (it) {
                is MypageViewModel.Event.Success -> name = it.data.name
            }
        }
    }
    var listPlusHeightPx by remember { mutableStateOf(0) }
    var isCollapsedAll = remember { false }
    var isExpandedAll = remember { true }

    val nestedScrollConnection =
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                return if (available.y < 0) {
                    isExpandedAll = false
                    listPlusHeightPx = available.y.toInt()
                    if (isCollapsedAll) Offset.Zero else available
                } else {
                    isCollapsedAll = false
                    listPlusHeightPx = available.y.toInt()
                    if (isExpandedAll) Offset.Zero else available
                }
            }
        }

    val minHeight = with(localDensity) { 460.dp.toPx() }
    val maxHeight = with(localDensity) { 620.dp.toPx() }

    var listHeight by remember { mutableStateOf(minHeight) }
    listHeight = (listHeight - listPlusHeightPx).coerceIn(minHeight, maxHeight)

    when (listHeight) {
        minHeight -> isCollapsedAll = true
        maxHeight -> isExpandedAll = true
    }

    val height = with(localDensity) { listHeight.toDp() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = QpidColor.Blue),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row {
                PreSemiBold32(
                    text = name,
                    color = QpidColor.White,
                )
                PreSemiBold32(
                    text = "님 안녕하세요!",
                    color = QpidColor.White_Blue,
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(align = Alignment.Bottom)
                .height(height)
                .background(
                    color = QpidColor.White,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .nestedScroll(nestedScrollConnection)
        ) {
            item {
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

                WebViewScreen(
                    navController = navController,
                    url = "http://192.168.137.1:3000/list/mylist"
                )
            }

        }
    }
}
package com.example.qpid_android.feature.write

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.qpid_android.R
import com.example.qpid_android.design_system.color.QpidColor
import com.example.qpid_android.design_system.component.ContentTextField
import com.example.qpid_android.design_system.component.Line
import com.example.qpid_android.design_system.component.TextCounter
import com.example.qpid_android.design_system.component.TitleTextField
import com.example.qpid_android.design_system.typograpy.PreBold20
import com.example.qpid_android.design_system.typograpy.PreMedium16
import com.example.qpid_android.util.rememberToast
import com.example.qpid_android.util.setLightStatusBar
import kotlinx.coroutines.flow.collect

internal object MenuItem {
    const val MOVIE = "영화관"
    const val FOOD = "음식점"
    const val BANK = "은행"
    const val ETC = "기타"
}

fun String.toMenu() =
    when(this) {
        MenuItem.MOVIE -> "MOVIE"
        MenuItem.FOOD -> "FOOD"
        MenuItem.BANK -> "BANK"
        MenuItem.ETC -> "ETC"
        else -> "ETC"
    }

@Composable
fun WriteScreen(
    navController: NavController,
    vm: WriteViewModel = hiltViewModel(),
    id: Int? = null,
    titleT: String = "",
    contentT: String = "",
    tagT: String = MenuItem.FOOD
) {
    val context = LocalContext.current
    context.setLightStatusBar()

    var titleText by remember { mutableStateOf(titleT) }
    var contentText by remember { mutableStateOf(contentT) }
    var tagText by remember { mutableStateOf(tagT) }

    val toast = rememberToast()
    LaunchedEffect(vm) {
        vm.eventFlow.collect {
            when (it) {
                is WriteViewModel.Event.Success ->
                    navController.popBackStack()
                is WriteViewModel.Event.Fail -> toast(it.message)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(QpidColor.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            Row {
                Spacer(modifier = Modifier.width(12.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                        ) {
                            navController.popBackStack()
                        }
                )
            }

            PreBold20(
                text = "글쓰기",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
            )

            PreMedium16(
                text = "등록",
                color = QpidColor.Blue,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.End)
                    .padding(end = 10.dp)
                    .width(60.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                    ) {
                        if (id == null) {
                            vm.writeFeed(titleText, contentText, tagText.toMenu())
                        } else {
                            vm.patchFeed(id, titleText, contentText, tagText.toMenu())
                        }
                    }
            )
        }

        Spacer(modifier = Modifier.height(5.dp))
        Line(
            lineColor = QpidColor.Gray100,
            lineHeight = 5f,
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            PreMedium16(text = "제목")
            Spacer(modifier = Modifier.height(20.dp))
            TitleTextField(
                text = titleText,
                onTextChange = {
                    if (it.length <= 20) {
                        titleText = it
                    }
                },
                placeholder = "제목을 입력해주세요"
            )
            TextCounter(
                length = titleText.length,
                total = 20,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.End)
            )

            Spacer(modifier = Modifier.height(20.dp))
            PreMedium16(text = "내용")
            Spacer(modifier = Modifier.height(20.dp))
            ContentTextField(
                text = contentText,
                onTextChange = {
                    if (it.length <= 1000) {
                        contentText = it
                    }
                },
                placeholder = "내용을 입력해주세요"
            )
            TextCounter(
                length = contentText.length,
                total = 1000,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.End)
            )

            Spacer(modifier = Modifier.height(20.dp))
            PreMedium16(text = "태그")
            Spacer(modifier = Modifier.height(20.dp))
            TagPicker(text = tagText, checkWhere = {tagText = it})
        }

    }
}

@Composable
fun TagPicker(
    text: String,
    checkWhere: (String) -> Unit
) {
    val menu = listOf(MenuItem.FOOD, MenuItem.BANK, MenuItem.MOVIE, MenuItem.ETC)
    var isDrop by remember { mutableStateOf(false) }
    var targetValue by remember { mutableStateOf(-90F) }
    val rotateValue: Float by animateFloatAsState(
        targetValue = targetValue,
        tween(300)
    )
    
    Column(
        modifier = Modifier
            .width(120.dp)
            .background(
                color = QpidColor.Gray200,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                ) {
                    isDrop = true
                    targetValue = 0F
                }
                .padding(horizontal = 4.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            PreMedium16(
                text = text,
                color = QpidColor.Gray500
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow),
                contentDescription = null,
                tint = QpidColor.Gray400,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.End)
                    .size(16.dp)
                    .rotate(rotateValue)
            )
        }
        DropdownMenu(
            expanded = isDrop,
            onDismissRequest = {
                isDrop = false
                targetValue = -90F
            },
            modifier = Modifier
                .width(120.dp)
                .background(QpidColor.Gray200)
        ) {
            menu.forEach {
                if (it != text) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Row {
                        Spacer(modifier = Modifier.width(10.dp))
                        PreMedium16(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(align = Alignment.Start)
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null,
                                ) {
                                    checkWhere(it)
                                    isDrop = false
                                    targetValue = -90F
                                }
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WritePreview() {
    val navController = rememberNavController()
    WriteScreen(navController = navController)
}
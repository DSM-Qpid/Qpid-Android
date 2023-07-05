package com.example.qpid_android.feature.write

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.example.qpid_android.util.setLightStatusBar

@Composable
fun WriteScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    context.setLightStatusBar()

    var titleText by rememberSaveable { mutableStateOf("") }
    var contentText by rememberSaveable { mutableStateOf("") }

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


        }

    }
}

@Preview(showBackground = true)
@Composable
fun WritePreview() {
    val navController = rememberNavController()
    WriteScreen(navController = navController)
}
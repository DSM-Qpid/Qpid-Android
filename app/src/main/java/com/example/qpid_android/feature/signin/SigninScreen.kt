package com.example.qpid_android.feature.signin

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.qpid_android.design_system.color.QpidColor
import com.example.qpid_android.design_system.component.QpidButton
import com.example.qpid_android.design_system.component.SignTextFiled
import com.example.qpid_android.design_system.typograpy.PreBold30
import com.example.qpid_android.design_system.typograpy.PreMedium12
import com.example.qpid_android.design_system.typograpy.PreMedium16
import com.example.qpid_android.navigation.QpidNavigationItem

@Composable
fun SigninScreen(
    navController: NavController,
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val btnEnabled = id.length >= 8 && password.length >= 8

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = QpidColor.White)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        PreBold30(text = "로그인")
        Spacer(modifier = Modifier.height(50.dp))
        SignTextFiled(
            title = "아이디",
            content = id,
            onTextChange = { id = it },
            placeholder = "8자 이상의 아이디 입력"
        )

        Spacer(modifier = Modifier.height(25.dp))

        SignTextFiled(
            title = "비밀번호",
            content = password,
            onTextChange = { password = it },
            placeholder = "",
            isSecret = true
        )
        PreMedium12(
            text = "영어,숫자,특수문자 포함 8자 이상",
            color = QpidColor.Blue,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.End)
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(align = Alignment.Bottom),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row {
                PreMedium16(
                    text = "회원이 아닌가요? ",
                    color = QpidColor.Gray500,
                )

                PreMedium16(
                    text = "회원가입",
                    color = QpidColor.Blue,
                    modifier = Modifier
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                        ) {
                            navController.navigate(QpidNavigationItem.Signup.route)
                        }
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            QpidButton(
                text = "로그인",
                isEnabled = btnEnabled
            ) {

            }

            Spacer(modifier = Modifier.height(25.dp))
        }
    }


}
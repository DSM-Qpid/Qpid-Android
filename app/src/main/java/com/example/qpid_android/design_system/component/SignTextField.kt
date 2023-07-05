package com.example.qpid_android.design_system.component

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qpid_android.R
import com.example.qpid_android.design_system.color.QpidColor
import com.example.qpid_android.design_system.typograpy.PreMedium20
import com.example.qpid_android.design_system.typograpy.PreRegular20
import com.example.qpid_android.design_system.typograpy.pretendardFamily

@Composable
fun SignTextFiled(
    title: String,
    content: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    isSecret: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {

    var passwordVisible by remember { mutableStateOf(true) }
    val passwordImage =
        if (passwordVisible) painterResource(id = R.drawable.ic_password_on)
        else painterResource(id = R.drawable.ic_password_off)

    val backgroundColor = if (content.isEmpty()) QpidColor.White else QpidColor.Gray100
    val textFiledShape = RoundedCornerShape(10.dp)

    Column {
        PreMedium20(text = title)
        Spacer(modifier = Modifier.height(5.dp))
        BasicTextField(
            value = content,
            onValueChange = onTextChange,
            singleLine = true,
            maxLines = 1,
            keyboardOptions = keyboardOptions,
            cursorBrush = SolidColor(QpidColor.Blue),
            visualTransformation =
            if (isSecret && passwordVisible) PasswordVisualTransformation()
            else VisualTransformation.None,
            textStyle = TextStyle(
                color = QpidColor.Black,
                fontSize = 20.sp,
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Normal,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .clip(textFiledShape)
                .background(
                    color = backgroundColor,
                    shape = textFiledShape,
                )
                .border(
                    width = 2.dp,
                    color = QpidColor.Gray200,
                    shape = textFiledShape,
                ),
            decorationBox = {
                Box(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                   if (content.isEmpty()) {
                       PreRegular20(
                           text = placeholder,
                           color = QpidColor.Gray300,
                       )
                   }
                    it()

                    if (isSecret) {
                        Image(
                            painter = passwordImage,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(align = Alignment.End)
                                .size(24.dp)
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null,
                                ) {
                                    passwordVisible = !passwordVisible
                                }
                        )
                    }

                }
            }

        )
    }

}
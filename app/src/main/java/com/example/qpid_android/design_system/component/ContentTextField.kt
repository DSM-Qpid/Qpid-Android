package com.example.qpid_android.design_system.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qpid_android.design_system.color.QpidColor
import com.example.qpid_android.design_system.typograpy.PreMedium16
import com.example.qpid_android.design_system.typograpy.pretendardFamily

@Composable
fun ContentTextField(
    text: String,
    onTextChange:(String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    BasicTextField(
        value = text,
        onValueChange = onTextChange,
        keyboardOptions = keyboardOptions,
        cursorBrush = SolidColor(QpidColor.Blue),
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Medium,
            color = QpidColor.Black,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(132.dp)
            .border(
                width = 1.dp,
                color = QpidColor.Gray200,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp),
        decorationBox = {
            Box {
                if (text.isEmpty()) {
                    PreMedium16(
                        text = placeholder,
                        color = QpidColor.Gray300,
                    )
                }
                it()
            }
        }
    )
}
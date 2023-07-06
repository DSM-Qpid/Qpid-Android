package com.example.qpid_android.design_system.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qpid_android.design_system.color.QpidColor
import com.example.qpid_android.design_system.typograpy.PreMedium12
import com.example.qpid_android.design_system.typograpy.PreMedium20
import com.example.qpid_android.design_system.typograpy.pretendardFamily

@Composable
fun TitleTextField(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {

    var focused by remember { mutableStateOf(false) }
    val lineColor = if (focused) QpidColor.Blue else QpidColor.Gray200

    BasicTextField(
        value = text,
        onValueChange = onTextChange,
        singleLine = true,
        maxLines = 1,
        keyboardOptions = keyboardOptions,
        cursorBrush = SolidColor(QpidColor.Blue),
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Medium,
            color = QpidColor.Black,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .onFocusChanged { focused = it.isFocused },
        decorationBox = {
            Box {
                if (text.isEmpty()) {
                    PreMedium20(
                        text = placeholder,
                        color = QpidColor.Gray300,
                    )
                }
                it()
                
                Line(
                    lineColor = lineColor,
                    lineHeight = 8f,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight(align = Alignment.Bottom)
                )
            }

        }
    )
}
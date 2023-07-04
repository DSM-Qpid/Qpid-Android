package com.example.qpid_android.design_system.component

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import com.example.qpid_android.design_system.color.QpidColor
import com.example.qpid_android.design_system.typograpy.PreMedium12

@Composable
fun TextCounter(
    length: Int,
    lengthColor: Color = QpidColor.Gray500,
    total: Int,
    totalColor: Color = QpidColor.Gray400,
    modifier: Modifier = Modifier,
) {
    val lengthColor = if (length <= 0) totalColor else lengthColor

    Row(modifier = modifier) {
        PreMedium12(
            text = length.toString(),
            color = lengthColor,
        )
        PreMedium12(
            text = buildAnnotatedString {
                append("/")
                append(total.toString())
            }.toString(),
            color = totalColor,
        )
    }
}
package com.example.qpid_android.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.qpid_android.design_system.color.QpidColor
import com.example.qpid_android.design_system.typograpy.PreMedium20

@Composable
fun QpidButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean,
    onClick: () -> Unit,
) {
    BasicButton(
        defaultColor = QpidColor.Blue,
        pressedColor = QpidColor.Dark_Blue,
        disabledColor = QpidColor.Gray300,
        isEnabled = isEnabled,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(10.dp)),
        onClick = onClick,
    ) {
        PreMedium20(text = text, color = QpidColor.White)
    }
}

@Composable
fun BasicButton(
    defaultColor: Color,
    pressedColor: Color,
    disabledColor: Color,
    isEnabled: Boolean,
    isLoading: Boolean = false,
    modifier: Modifier,
    onClick: () -> Unit,
    contents: @Composable (isPressed: Boolean) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    val buttonColor =
        if (!isEnabled) disabledColor else if (isPressed.value) pressedColor else defaultColor
    Box(
        modifier = modifier
            .background(buttonColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = isEnabled && !isLoading
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        contents(isPressed.value)
    }
}

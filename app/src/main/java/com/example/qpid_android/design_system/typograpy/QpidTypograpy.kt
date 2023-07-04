package com.example.qpid_android.design_system.typograpy

import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.qpid_android.R

@Composable
fun PreBold12(
    text: String,
    modifier: Modifier = Modifier,
    lineHeight: Int = 0,
    letterSpacing: Int = 0,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    Typography(
        text = text,
        modifier = modifier,
        weight = FontWeight.Bold,
        size = 12,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        baselineToTop = 10f,
        baselineToBottom = 10f,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}


@Composable
fun Typography(
    text: String,
    modifier: Modifier = Modifier,
    weight: FontWeight,
    size: Int,
    color: Color,
    lineHeight: Int,
    letterSpacing: Int,
    baselineToTop: Float,
    baselineToBottom: Float,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign?,
    overflow: TextOverflow,
    softWrap: Boolean,
    maxLines: Int,
    onTextLayout: (TextLayoutResult) -> Unit
) {

    val baselineModifier =
        modifier.paddingFromBaseline(top = baselineToTop.sp, bottom = baselineToBottom.sp)
    Text(
        style = TextStyle(
            color = color,
            lineHeight = lineHeight.sp,
            letterSpacing = letterSpacing.sp,
            fontSize = size.sp,
            fontFamily = pretendardFamily,
            fontWeight = weight,
            textDecoration = textDecoration,
            textAlign = textAlign,
        ),
        text = text,
        modifier = baselineModifier,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
    )
}

val pretendardFamily = FontFamily(
    Font(R.font.pretendard_black, FontWeight.Black),
    Font(R.font.pretendard_extrabold, FontWeight.ExtraBold),
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_light, FontWeight.Light),
    Font(R.font.pretendard_extralight, FontWeight.ExtraLight),
    Font(R.font.pretendard_thin, FontWeight.Thin),
)

package com.footra.app.ui.theme

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * The Figma frames are 1080 x 2400 px. Every number in the UI code is the Figma px value;
 * it is converted with  dp = px * (screenWidthDp / 1080)  so the layout is proportionally identical
 * to the design on every phone.
 */
val LocalDesignScale = compositionLocalOf { 1f }

/** Figma px -> Dp.  Example: 40.dx */
val Number.dx: Dp
    @Composable @ReadOnlyComposable get() = (this.toFloat() * LocalDesignScale.current).dp

/** Figma font size px -> sp.  Example: 24.fs */
val Number.fs: TextUnit
    @Composable @ReadOnlyComposable get() = (this.toFloat() * LocalDesignScale.current).sp

/**
 * Text with Figma-style parameters (size in Figma px, line height / letter spacing in %).
 * Uses BasicText so Material's default line height / letter spacing do not interfere.
 */
@Composable
fun FText(
    text: String,
    font: FontFamily,
    size: Number,
    modifier: Modifier = Modifier,
    weight: FontWeight = FontWeight.Normal,
    color: Color = FootraColors.Black,
    italic: Boolean = false,
    lineHeightPct: Float? = null,
    letterSpacingPct: Float? = null,
    align: TextAlign = TextAlign.Start,
    maxLines: Int = Int.MAX_VALUE,
    underline: Boolean = false
) {
    val px = size.toFloat() * LocalDesignScale.current
    BasicText(
        text = text,
        modifier = modifier,
        style = TextStyle(
            color = color,
            fontFamily = font,
            fontWeight = weight,
            fontStyle = if (italic) FontStyle.Italic else FontStyle.Normal,
            fontSize = px.sp,
            lineHeight = if (lineHeightPct != null) (px * lineHeightPct / 100f).sp else TextUnit.Unspecified,
            letterSpacing = if (letterSpacingPct != null) (px * letterSpacingPct / 100f).sp else TextUnit.Unspecified,
            textAlign = align,
            textDecoration = if (underline) TextDecoration.Underline else null
        ),
        maxLines = maxLines
    )
}

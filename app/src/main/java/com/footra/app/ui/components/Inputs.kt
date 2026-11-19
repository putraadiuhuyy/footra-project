package com.footra.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.footra.app.ui.theme.FText
import com.footra.app.ui.theme.FootraColors
import com.footra.app.ui.theme.FootraFonts
import com.footra.app.ui.theme.dx
import com.footra.app.ui.theme.fs

/**
 * Figma component "Input Field": label (Poppins Medium 22), gap 10, box 72 high, fill #D9D9D9,
 * padding 24/16, placeholder Poppins 20 (#6B6B6B). Works as a real text field.
 */
@Composable
fun FootraTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    boxHeight: Number = 72,
    topAligned: Boolean = false,
    singleLine: Boolean = true,
    password: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    val focusRequester = remember { FocusRequester() }
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dx)) {
        FText(label, FootraFonts.Poppins, 22, weight = FontWeight.Medium)
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = TextStyle(fontFamily = FootraFonts.Poppins, fontSize = 20.fs, color = FootraColors.Black),
            cursorBrush = SolidColor(FootraColors.Black),
            visualTransformation = if (password) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier
                .fillMaxWidth()
                .height(boxHeight.dx)
                .background(FootraColors.GrayLight)
                .focusRequester(focusRequester)
                .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) {
                    focusRequester.requestFocus()
                },
            decorationBox = { inner ->
                Box(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 24.dx, vertical = 16.dx),
                    contentAlignment = if (topAligned) Alignment.TopStart else Alignment.CenterStart
                ) {
                    if (value.isEmpty()) {
                        FText(placeholder, FootraFonts.Poppins, 20, color = FootraColors.Hint)
                    }
                    inner()
                }
            }
        )
    }
}

/** Figma component "Button": black pill, 72 high, radius 36, Poppins SemiBold 24 white. */
@Composable
fun PrimaryButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dx)
            .clip(RoundedCornerShape(36.dx))
            .background(FootraColors.Black)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        FText(
            text = text, font = FootraFonts.Poppins, size = 24, weight = FontWeight.SemiBold,
            color = FootraColors.White, align = TextAlign.Center
        )
    }
}

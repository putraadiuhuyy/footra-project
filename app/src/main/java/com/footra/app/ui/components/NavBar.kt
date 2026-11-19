package com.footra.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.footra.app.navigation.FootraNavigator
import com.footra.app.ui.theme.FText
import com.footra.app.ui.theme.FootraColors
import com.footra.app.ui.theme.FootraFonts
import com.footra.app.ui.theme.dx
import com.footra.app.ui.theme.fs

/**
 * Figma component "Navbar": 1080 wide, padding 40/30, logo + links (gap 28) + search 190x48 + avatar 64.
 * Logo -> Home, links -> their screens, avatar -> Login.
 */
@Composable
fun FootraNavBar(nav: FootraNavigator, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(FootraColors.White)
            .statusBarsPadding()
            .padding(horizontal = 40.dx, vertical = 30.dx),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FText(
            text = "FOOTRA", font = FootraFonts.Syne, size = 38, weight = FontWeight.ExtraBold,
            modifier = Modifier.clickable(onClick = nav::home)
        )
        Row(horizontalArrangement = Arrangement.spacedBy(28.dx)) {
            NavLink("Home", nav::home)
            NavLink("Product", nav::product)
            NavLink("About", nav::about)
            NavLink("Contact", nav::contact)
        }
        SearchPill()
        ProfileAvatar(onClick = nav::login)
    }
}

@Composable
private fun NavLink(text: String, onClick: () -> Unit) {
    FText(
        text = text, font = FootraFonts.Poppins, size = 24, weight = FontWeight.Medium,
        modifier = Modifier.clickable(onClick = onClick)
    )
}

/** Search pill: 190x48, radius 24, fill #9E9E9E, text 16 (#333). */
@Composable
private fun SearchPill() {
    var query by remember { mutableStateOf("") }
    BasicTextField(
        value = query,
        onValueChange = { query = it },
        singleLine = true,
        textStyle = TextStyle(fontFamily = FootraFonts.Poppins, fontSize = 16.fs, color = FootraColors.Body333),
        cursorBrush = SolidColor(Color.Black),
        modifier = Modifier
            .width(190.dx)
            .height(48.dx)
            .clip(RoundedCornerShape(24.dx))
            .background(FootraColors.GrayPill),
        decorationBox = { inner ->
            Box(
                modifier = Modifier.fillMaxSize().padding(horizontal = 22.dx),
                contentAlignment = Alignment.CenterStart
            ) {
                if (query.isEmpty()) {
                    FText("Search", FootraFonts.Poppins, 16, color = FootraColors.Body333)
                }
                inner()
            }
        }
    )
}

/** 64x64 profile icon, same shapes as the Figma SVG. */
@Composable
fun ProfileAvatar(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .size(64.dx)
            .clip(CircleShape)
            .clickable(onClick = onClick)
    ) {
        val k = size.minDimension / 64f
        drawCircle(Color.White)
        drawCircle(Color.Black, radius = 29f * k, style = Stroke(width = 5f * k))
        drawCircle(Color.Black, radius = 10f * k, center = Offset(32f * k, 26f * k))

        val body = Path().apply {
            moveTo(12f * k, 54f * k)
            cubicTo(16f * k, 42f * k, 24f * k, 38f * k, 32f * k, 38f * k)
            cubicTo(40f * k, 38f * k, 48f * k, 42f * k, 52f * k, 54f * k)
            cubicTo(46f * k, 60f * k, 40f * k, 62f * k, 32f * k, 62f * k)
            cubicTo(24f * k, 62f * k, 18f * k, 60f * k, 12f * k, 54f * k)
            close()
        }
        val clip = Path().apply { addOval(Rect(center = Offset(32f * k, 32f * k), radius = 27f * k)) }
        clipPath(clip) { drawPath(body, Color.Black) }
    }
}

/** "^" chevron of the filter pills (22x22 in Figma). */
@Composable
fun ChevronUp(modifier: Modifier = Modifier, color: Color = Color.Black) {
    Canvas(modifier.size(22.dx)) {
        val k = size.minDimension / 22f
        val path = Path().apply {
            moveTo(4f * k, 14f * k)
            lineTo(11f * k, 7f * k)
            lineTo(18f * k, 14f * k)
        }
        drawPath(path, color, style = Stroke(width = 2.6f * k, cap = StrokeCap.Round, join = StrokeJoin.Round))
    }
}

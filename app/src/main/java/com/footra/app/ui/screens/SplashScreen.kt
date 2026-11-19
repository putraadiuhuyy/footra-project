package com.footra.app.ui.screens

import android.app.Activity
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.core.view.WindowCompat
import com.footra.app.ui.theme.FText
import com.footra.app.ui.theme.FootraColors
import com.footra.app.ui.theme.FootraFonts
import com.footra.app.ui.theme.dx
import kotlinx.coroutines.delay

/** Figma "ease out" curve. */
val FigmaEaseOut = CubicBezierEasing(0f, 0f, 0.58f, 1f)

/**
 * Figma "Splash 1" -> "Splash 2" (smart animate 0.9s, ease out) -> Home (dissolve 0.5s after 1.4s).
 *
 *  Splash 1: logo 56px, accent 40x6, tagline, all opacity 0
 *  Splash 2: logo 96px, accent 260x6, tagline, all opacity 1
 *  Black background, items centered, gap 32.
 */
@Composable
fun SplashScreen(onFinished: () -> Unit) {
    // Light status/navigation bar icons on the black splash, restored afterwards.
    val view = LocalView.current
    DisposableEffect(Unit) {
        val window = (view.context as? Activity)?.window
        val controller = window?.let { WindowCompat.getInsetsController(it, view) }
        controller?.isAppearanceLightStatusBars = false
        controller?.isAppearanceLightNavigationBars = false
        onDispose {
            controller?.isAppearanceLightStatusBars = true
            controller?.isAppearanceLightNavigationBars = true
        }
    }

    var started by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (started) 1f else 0f,
        animationSpec = tween(durationMillis = 900, easing = FigmaEaseOut),
        label = "splashProgress"
    )
    val finished by rememberUpdatedState(onFinished)

    LaunchedEffect(Unit) {
        delay(300)      // Splash 1 is visible for 0.3s
        started = true  // smart animate to Splash 2
        delay(1400)     // Splash 2 holds 1.4s, then dissolve to Home
        finished()
    }

    Box(
        modifier = Modifier.fillMaxSize().background(FootraColors.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dx)
        ) {
            FText(
                text = "FOOTRA", font = FootraFonts.Syne,
                size = 56f + 40f * progress, // 56 -> 96
                weight = FontWeight.ExtraBold, color = FootraColors.White,
                modifier = Modifier.graphicsLayer { alpha = progress }
            )
            Box(
                Modifier
                    .width((40f + 220f * progress).dx) // 40 -> 260
                    .height(6.dx)
                    .graphicsLayer { alpha = progress }
                    .background(FootraColors.White)
            )
            FText(
                text = "STEP INTO STYLE", font = FootraFonts.Poppins, size = 26,
                weight = FontWeight.Medium, color = FootraColors.GrayLight,
                letterSpacingPct = 25f,
                modifier = Modifier.graphicsLayer { alpha = progress }
            )
        }
    }
}

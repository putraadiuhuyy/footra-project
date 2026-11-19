package com.footra.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.footra.app.navigation.FootraNavigator
import com.footra.app.ui.components.FootraFooter
import com.footra.app.ui.components.FootraNavBar
import com.footra.app.ui.components.PhotoBox
import com.footra.app.ui.FootraImages
import com.footra.app.ui.theme.FText
import com.footra.app.ui.theme.FootraColors
import com.footra.app.ui.theme.FootraFonts
import com.footra.app.ui.theme.dx

/** Figma "About": title, banner (935 high), our story, 4 stats, 3 value cards. */
@Composable
fun AboutScreen(nav: FootraNavigator) {
    Column(Modifier.fillMaxSize().background(FootraColors.White)) {
        FootraNavBar(nav)
        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
            Column(
                modifier = Modifier.padding(start = 40.dx, end = 40.dx, top = 10.dx, bottom = 40.dx),
                verticalArrangement = Arrangement.spacedBy(40.dx)
            ) {
                FText("ABOUT FOOTRA", FootraFonts.Syne, 44, weight = FontWeight.ExtraBold)

                PhotoBox(FootraImages.aboutBanner, Modifier.fillMaxWidth().height(935.dx), FootraColors.GrayMid) // About Banner

                // Our Story: image 420x400, gap 40, text column gap 18
                Row(horizontalArrangement = Arrangement.spacedBy(40.dx)) {
                    PhotoBox(FootraImages.aboutStory, Modifier.width(420.dx).height(400.dx), FootraColors.GrayLight)
                    Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(18.dx)) {
                        FText("Our Story", FootraFonts.Syne, 36, weight = FontWeight.ExtraBold)
                        FText(
                            text = "FOOTRA is a home for sneaker lovers. We started with a simple idea: bring " +
                                "authentic adidas classics and fresh City Series drops to everyone, at fair prices " +
                                "and with fast delivery. Every pair is checked before it ships, so you can step out " +
                                "with confidence.",
                            font = FootraFonts.Poppins, size = 22, color = FootraColors.Body, lineHeightPct = 150f
                        )
                    }
                }

                // Stats: gap 20, cards #ADADAD, padding 32/16, gap 6
                Row(horizontalArrangement = Arrangement.spacedBy(20.dx)) {
                    listOf("5K+" to "Happy Customers", "200+" to "Products", "50+" to "Brands", "24/7" to "Support")
                        .forEach { (number, label) ->
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(FootraColors.GrayMid)
                                    .padding(horizontal = 16.dx, vertical = 32.dx),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(6.dx)
                            ) {
                                FText(number, FootraFonts.Syne, 44, weight = FontWeight.ExtraBold)
                                FText(label, FootraFonts.Poppins, 20, align = TextAlign.Center)
                            }
                        }
                }

                // Values: gap 20, cards #D9D9D9, padding 28/36, gap 12
                Row(horizontalArrangement = Arrangement.spacedBy(20.dx)) {
                    listOf(
                        "100% Authentic" to "Every product is original and checked before it ships.",
                        "Fast Delivery" to "Orders are packed quickly and sent across Indonesia.",
                        "Best Deals" to "Fair prices and regular deals on your favourite pairs."
                    ).forEach { (heading, text) ->
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .background(FootraColors.GrayLight)
                                .padding(horizontal = 28.dx, vertical = 36.dx),
                            verticalArrangement = Arrangement.spacedBy(12.dx)
                        ) {
                            FText(heading, FootraFonts.Poppins, 26, weight = FontWeight.SemiBold)
                            FText(text, FootraFonts.Poppins, 20, color = FootraColors.Body333)
                        }
                    }
                }
            }
            FootraFooter(nav)
        }
    }
}

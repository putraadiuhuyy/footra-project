package com.footra.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import com.footra.app.navigation.FootraNavigator
import com.footra.app.ui.components.DealCard
import com.footra.app.ui.components.FootraFooter
import com.footra.app.ui.components.FootraNavBar
import com.footra.app.ui.components.PhotoBox
import com.footra.app.ui.FootraImages
import com.footra.app.ui.theme.FText
import com.footra.app.ui.theme.FootraColors
import com.footra.app.ui.theme.FootraFonts
import com.footra.app.ui.theme.dx

/**
 * Figma "Home": content padding 40 (top 10, bottom 40), gap 40.
 * Hero 589 high, Best Deals (4 cards, gap 20), collection banner, 2 promo boxes (300 high).
 */
@Composable
fun HomeScreen(nav: FootraNavigator) {
    Column(Modifier.fillMaxSize().background(FootraColors.White)) {
        FootraNavBar(nav)
        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
            Column(
                modifier = Modifier.padding(start = 40.dx, end = 40.dx, top = 10.dx, bottom = 40.dx),
                verticalArrangement = Arrangement.spacedBy(40.dx)
            ) {
                PhotoBox(FootraImages.homeHero, Modifier.fillMaxWidth().height(589.dx), FootraColors.GrayMid) // Hero Banner

                // Best Deals
                Column(verticalArrangement = Arrangement.spacedBy(20.dx)) {
                    FText("BEST DEALS", FootraFonts.Syne, 34, weight = FontWeight.ExtraBold)
                    Row(horizontalArrangement = Arrangement.spacedBy(20.dx)) {
                        repeat(4) { DealCard(onClick = nav::product, image = FootraImages.deals, modifier = Modifier.weight(1f)) }
                    }
                }

                CollectionBanner(nav)

                // Promo row
                Row(horizontalArrangement = Arrangement.spacedBy(20.dx)) {
                    repeat(2) { i -> PhotoBox(FootraImages.promo.getOrNull(i), Modifier.weight(1f).height(300.dx), FootraColors.GrayMid) }
                }
            }
            FootraFooter(nav)
        }
    }
}

/** Banner: fill #ADADAD, padding 40, gap 50; image 290x400; thumbs 126 (gap 14); BUY NOW pill. */
@Composable
private fun CollectionBanner(nav: FootraNavigator) {
    Row(
        modifier = Modifier.fillMaxWidth().background(FootraColors.GrayMid).padding(40.dx),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(50.dx)
    ) {
        PhotoBox(FootraImages.collectionMain, Modifier.size(290.dx), FootraColors.GrayLight)
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(20.dx)) {
            FText("Adidas Series", FootraFonts.ArchivoItalic, 46, weight = FontWeight.Black, italic = true)
            FText("Collection", FootraFonts.ArchivoItalic, 38, weight = FontWeight.Bold, italic = true)
            Row(horizontalArrangement = Arrangement.spacedBy(14.dx)) {
                repeat(4) { i -> PhotoBox(FootraImages.collectionThumbs.getOrNull(i), Modifier.size(126.dx), FootraColors.GrayLight) }
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dx))
                    .background(FootraColors.White)
                    .clickable(onClick = nav::product)
                    .padding(horizontal = 30.dx, vertical = 12.dx)
            ) {
                FText("BUY NOW", FootraFonts.Archivo, 20, weight = FontWeight.ExtraBold)
            }
        }
    }
}

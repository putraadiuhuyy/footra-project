package com.footra.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.footra.app.ui.theme.FText
import com.footra.app.ui.theme.FootraColors
import com.footra.app.ui.theme.FootraFonts
import com.footra.app.ui.theme.dx

/**
 * Foto yang dipotong (crop) memenuhi kotak. Selama [model] null / belum termuat, tampil warna placeholder.
 * [model] = R.drawable.xxx, URL String, atau null.
 */
@Composable
fun PhotoBox(model: Any?, modifier: Modifier = Modifier, placeholder: Color = FootraColors.GrayDark) {
    Box(modifier.background(placeholder)) {
        if (model != null) {
            AsyncImage(
                model = model,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

/** Figma "Deal Card": fill #ADADAD, padding 16, gap 16, image 240 high, centered text. */
@Composable
fun DealCard(onClick: () -> Unit, image: Any? = null, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(FootraColors.GrayMid)
            .clickable(onClick = onClick)
            .padding(16.dx),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dx)
    ) {
        FText(
            text = "Name Product", font = FootraFonts.Poppins, size = 22, weight = FontWeight.Medium,
            align = TextAlign.Center, modifier = Modifier.fillMaxWidth()
        )
        PhotoBox(image, Modifier.fillMaxWidth().height(240.dx))
        FText(
            text = "Rp. 100.000,00", font = FootraFonts.Cormorant, size = 24, weight = FontWeight.Medium,
            align = TextAlign.Center, modifier = Modifier.fillMaxWidth()
        )
    }
}

/** Figma "Product Card": fill #D9D9D9, padding 16 (bottom 20), gap 14, image 285 high. */
@Composable
fun ProductCard(name: String, price: String, image: Any? = null, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(FootraColors.GrayLight)
            .padding(start = 16.dx, top = 16.dx, end = 16.dx, bottom = 20.dx),
        verticalArrangement = Arrangement.spacedBy(14.dx)
    ) {
        PhotoBox(image, Modifier.fillMaxWidth().height(285.dx))
        FText(
            text = name, font = FootraFonts.Poppins, size = 20, weight = FontWeight.Medium,
            modifier = Modifier.fillMaxWidth()
        )
        FText(text = price, font = FootraFonts.Cormorant, size = 24, weight = FontWeight.Medium)
    }
}

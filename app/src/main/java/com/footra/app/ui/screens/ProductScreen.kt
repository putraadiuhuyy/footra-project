package com.footra.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import com.footra.app.navigation.FootraNavigator
import com.footra.app.ui.components.ChevronUp
import com.footra.app.ui.components.FootraFooter
import com.footra.app.ui.components.FootraNavBar
import com.footra.app.ui.components.ProductCard
import com.footra.app.ui.FootraImages
import com.footra.app.ui.theme.FText
import com.footra.app.ui.theme.FootraColors
import com.footra.app.ui.theme.FootraFonts
import com.footra.app.ui.theme.dx

private data class Product(val name: String, val price: String, val image: Any?)

/**
 * Figma "Product": content padding 40 (top 40, bottom 90), gap 70.
 * Filters (3 pills 300x56, gap 12) - scroll HORIZONTALLY.
 * "ALL PRODUCT" + 3x3 grid (column gap 24, row gap 118) - whole page scrolls VERTICALLY.
 */
@Composable
fun ProductScreen(nav: FootraNavigator) {
    var type by remember { mutableStateOf<String?>(null) }
    var size by remember { mutableStateOf<String?>(null) }
    var price by remember { mutableStateOf<String?>(null) }

    val verticalScroll = rememberScrollState()
    val filterScroll = rememberScrollState()

    // Same 9 sample items as the Figma grid (replace with real data later).
    val products = remember { List(9) { i -> Product("adidas Handball Spezial Navy Gum", "Rp. 1.999.000,00", FootraImages.products.getOrNull(i)) } }

    Column(Modifier.fillMaxSize().background(FootraColors.White)) {
        FootraNavBar(nav)
        Column(Modifier.weight(1f).verticalScroll(verticalScroll)) {
            Column(
                modifier = Modifier.padding(start = 40.dx, end = 40.dx, top = 40.dx, bottom = 90.dx),
                verticalArrangement = Arrangement.spacedBy(70.dx)
            ) {
                // Filters: centered when they fit, swipe horizontally when they do not.
                BoxWithConstraints(Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .horizontalScroll(filterScroll)
                            .widthIn(min = maxWidth),
                        horizontalArrangement = Arrangement.spacedBy(12.dx, Alignment.CenterHorizontally)
                    ) {
                        FilterPill("Product Type", listOf("Sneakers", "Apparel", "Accessories"), type) { type = it }
                        FilterPill("Size", listOf("38", "39", "40", "41", "42", "43", "44"), size) { size = it }
                        FilterPill(
                            "Price",
                            listOf("< Rp. 1.000.000", "Rp. 1.000.000 - 2.000.000", "> Rp. 2.000.000"),
                            price
                        ) { price = it }
                    }
                }

                FText("ALL PRODUCT", FootraFonts.Syne, 44, weight = FontWeight.ExtraBold)

                Column(verticalArrangement = Arrangement.spacedBy(118.dx)) {
                    products.chunked(3).forEach { rowItems ->
                        Row(horizontalArrangement = Arrangement.spacedBy(24.dx)) {
                            rowItems.forEach { p -> ProductCard(p.name, p.price, p.image, Modifier.weight(1f)) }
                        }
                    }
                }
            }
            FootraFooter(nav)
        }
    }
}

/** Filter pill: 300x56, radius 22, fill #9E9E9E, padding 24/20, Poppins Medium 20 + chevron 22. */
@Composable
private fun FilterPill(
    title: String,
    options: List<String>,
    selected: String?,
    onSelect: (String?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        Row(
            modifier = Modifier
                .width(300.dx)
                .height(56.dx)
                .clip(RoundedCornerShape(22.dx))
                .background(FootraColors.GrayPill)
                .clickable { expanded = true }
                .padding(start = 24.dx, end = 20.dx),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FText(
                text = selected ?: title, font = FootraFonts.Poppins, size = 20,
                weight = FontWeight.Medium, maxLines = 1, modifier = Modifier.weight(1f)
            )
            ChevronUp()
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = { FText("All", FootraFonts.Poppins, 20) },
                onClick = { onSelect(null); expanded = false }
            )
            options.forEach { option ->
                DropdownMenuItem(
                    text = { FText(option, FootraFonts.Poppins, 20) },
                    onClick = { onSelect(option); expanded = false }
                )
            }
        }
    }
}

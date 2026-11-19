package com.footra.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.footra.app.navigation.FootraNavigator
import com.footra.app.ui.theme.FText
import com.footra.app.ui.theme.FootraColors
import com.footra.app.ui.theme.FootraFonts
import com.footra.app.ui.theme.dx

/** Figma component "Footer": fill #262626, padding 40/60, gap 70; columns gap 50, links gap 14. */
@Composable
fun FootraFooter(nav: FootraNavigator, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(FootraColors.Footer)
            .navigationBarsPadding()
            .padding(horizontal = 40.dx, vertical = 60.dx),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(70.dx)
    ) {
        FText(
            text = "FOOTRA", font = FootraFonts.Syne, size = 34, weight = FontWeight.ExtraBold,
            color = FootraColors.White, modifier = Modifier.clickable(onClick = nav::home)
        )
        Row(horizontalArrangement = Arrangement.spacedBy(50.dx)) {
            FooterColumn(
                "Shop",
                listOf("Online Retail" to nav::product, "Get Started" to nav::signUp, "Testimonials" to null)
            )
            FooterColumn(
                "Help",
                listOf("FAQ" to null, "Support" to nav::contact, "Knowledge Base" to null)
            )
            FooterColumn(
                "About",
                listOf("Contact" to nav::contact, "Services" to nav::about, "Team" to nav::about)
            )
        }
    }
}

@Composable
private fun FooterColumn(title: String, links: List<Pair<String, (() -> Unit)?>>) {
    Column(verticalArrangement = Arrangement.spacedBy(14.dx)) {
        FText(title, FootraFonts.BebasNeue, 38, color = FootraColors.White)
        links.forEach { (label, action) ->
            FText(
                text = label, font = FootraFonts.Cormorant, size = 28, weight = FontWeight.Medium,
                color = FootraColors.GrayLight,
                modifier = if (action != null) Modifier.clickable(onClick = action) else Modifier
            )
        }
    }
}

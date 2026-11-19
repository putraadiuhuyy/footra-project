@file:OptIn(androidx.compose.ui.text.ExperimentalTextApi::class)

package com.footra.app.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import com.footra.app.R

/** Font files live in res/font (Google Fonts, OFL license). Same families as the Figma file. */
object FootraFonts {
    val Syne = FontFamily(
        Font(R.font.syne, FontWeight.ExtraBold, variationSettings = FontVariation.Settings(FontVariation.weight(800)))
    )

    val Poppins = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold)
    )

    val ArchivoItalic = FontFamily(
        Font(R.font.archivo_italic, FontWeight.Black, FontStyle.Italic, variationSettings = FontVariation.Settings(FontVariation.weight(900))),
        Font(R.font.archivo_italic, FontWeight.Bold, FontStyle.Italic, variationSettings = FontVariation.Settings(FontVariation.weight(700)))
    )

    val Archivo = FontFamily(
        Font(R.font.archivo, FontWeight.ExtraBold, variationSettings = FontVariation.Settings(FontVariation.weight(800)))
    )

    val BebasNeue = FontFamily(Font(R.font.bebas_neue, FontWeight.Normal))

    val Cormorant = FontFamily(
        Font(R.font.cormorant_garamond, FontWeight.Normal, variationSettings = FontVariation.Settings(FontVariation.weight(400))),
        Font(R.font.cormorant_garamond, FontWeight.Medium, variationSettings = FontVariation.Settings(FontVariation.weight(500)))
    )
}

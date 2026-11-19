package com.footra.app.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import com.footra.app.navigation.FootraNavigator
import com.footra.app.ui.components.FootraFooter
import com.footra.app.ui.components.FootraNavBar
import com.footra.app.ui.components.FootraTextField
import com.footra.app.ui.components.PrimaryButton
import com.footra.app.ui.theme.FText
import com.footra.app.ui.theme.FootraColors
import com.footra.app.ui.theme.FootraFonts
import com.footra.app.ui.theme.dx

/** Figma "Contact": title, subtitle, form card + info card (360 wide), map placeholder (970 high). */
@Composable
fun ContactScreen(nav: FootraNavigator) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().background(FootraColors.White)) {
        FootraNavBar(nav)
        Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
            Column(
                modifier = Modifier.padding(start = 40.dx, end = 40.dx, top = 10.dx, bottom = 40.dx),
                verticalArrangement = Arrangement.spacedBy(40.dx)
            ) {
                FText("CONTACT US", FootraFonts.Syne, 44, weight = FontWeight.ExtraBold)
                FText(
                    text = "Have a question about an order or a product? Send us a message and we will get back to you soon.",
                    font = FootraFonts.Poppins, size = 22, color = FootraColors.Body, lineHeightPct = 150f
                )

                Row(
                    modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
                    horizontalArrangement = Arrangement.spacedBy(24.dx)
                ) {
                    // Form card: fill #D9D9D9, padding 40, gap 24
                    Column(
                        modifier = Modifier.weight(1f).background(FootraColors.GrayLight).padding(40.dx),
                        verticalArrangement = Arrangement.spacedBy(24.dx)
                    ) {
                        FootraTextField("Name", name, { name = it }, "Your full name")
                        FootraTextField("Email", email, { email = it }, "you@example.com", keyboardType = KeyboardType.Email)
                        FootraTextField("Subject", subject, { subject = it }, "How can we help?")
                        FootraTextField(
                            "Message", message, { message = it }, "Write your message here...",
                            topAligned = true, singleLine = false
                        )
                        PrimaryButton("Send Message", onClick = {
                            if (name.isBlank() || email.isBlank() || message.isBlank()) {
                                Toast.makeText(context, "Please fill in name, email and message", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Message sent!", Toast.LENGTH_SHORT).show()
                                name = ""; email = ""; subject = ""; message = ""
                            }
                        })
                    }

                    // Info card: fill #ADADAD, width 360, padding 40, gap 28
                    Column(
                        modifier = Modifier
                            .width(360.dx)
                            .fillMaxHeight()
                            .background(FootraColors.GrayMid)
                            .padding(40.dx),
                        verticalArrangement = Arrangement.spacedBy(28.dx)
                    ) {
                        FText("GET IN TOUCH", FootraFonts.Syne, 30, weight = FontWeight.ExtraBold)
                        listOf(
                            "Email" to "hello@footra.id",
                            "Phone" to "+62 812-3456-7890",
                            "Address" to "Yogyakarta, Indonesia",
                            "Open" to "Mon - Sat, 09.00 - 21.00"
                        ).forEach { (label, value) ->
                            Column(verticalArrangement = Arrangement.spacedBy(4.dx)) {
                                FText(label, FootraFonts.Poppins, 18, weight = FontWeight.Medium, color = FootraColors.Body333)
                                FText(value, FootraFonts.Poppins, 22)
                            }
                        }
                    }
                }

                Box(Modifier.fillMaxWidth().height(970.dx).background(FootraColors.GrayMid)) // Map Placeholder
            }
            FootraFooter(nav)
        }
    }
}

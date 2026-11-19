package com.footra.app.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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

/** Figma "Login": Login -> Home, "Sign Up" -> Sign Up. */
@Composable
fun LoginScreen(nav: FootraNavigator) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    AuthScaffold(nav) {
        AuthCard(title = "LOGIN", subtitle = "Welcome back! Log in to your FOOTRA account.") {
            FootraTextField("Email", email, { email = it }, "you@example.com", keyboardType = KeyboardType.Email)
            FootraTextField(
                "Password", password, { password = it }, "Enter your password",
                password = true, keyboardType = KeyboardType.Password
            )
            FText(
                text = "Forgot password?", font = FootraFonts.Poppins, size = 18, weight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.End)
            )
            PrimaryButton("Login", onClick = {
                if (email.isBlank() || password.isBlank()) {
                    Toast.makeText(context, "Please enter your email and password", Toast.LENGTH_SHORT).show()
                } else {
                    nav.home()
                }
            })
            SwitchRow("Don't have an account?", "Sign Up", onClick = nav::signUp)
        }
    }
}

/** Figma "Sign Up": Create Account -> Login, "Login" -> Login. */
@Composable
fun SignUpScreen(nav: FootraNavigator) {
    val context = LocalContext.current
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }

    AuthScaffold(nav) {
        AuthCard(title = "SIGN UP", subtitle = "Create your FOOTRA account to start shopping.") {
            FootraTextField("Full Name", fullName, { fullName = it }, "Your full name")
            FootraTextField("Email", email, { email = it }, "you@example.com", keyboardType = KeyboardType.Email)
            FootraTextField(
                "Password", password, { password = it }, "Create a password",
                password = true, keyboardType = KeyboardType.Password
            )
            FootraTextField(
                "Confirm Password", confirm, { confirm = it }, "Repeat your password",
                password = true, keyboardType = KeyboardType.Password
            )
            PrimaryButton("Create Account", onClick = {
                when {
                    fullName.isBlank() || email.isBlank() || password.isBlank() ->
                        Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    password != confirm ->
                        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                    else -> nav.login()
                }
            })
            SwitchRow("Already have an account?", "Login", onClick = nav::login)
        }
    }
}

/** Navbar + card centered in the free area (padding top 10 / bottom 40) + footer. */
@Composable
private fun AuthScaffold(nav: FootraNavigator, content: @Composable () -> Unit) {
    Column(Modifier.fillMaxSize().background(FootraColors.White).imePadding()) {
        FootraNavBar(nav)
        BoxWithConstraints(Modifier.weight(1f).fillMaxWidth()) {
            val minHeight = maxHeight
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .heightIn(min = minHeight)
                    .padding(top = 10.dx, bottom = 40.dx),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                content()
            }
        }
        FootraFooter(nav)
    }
}

/** Auth card: 760 wide, fill #ADADAD, padding 56, gap 26. */
@Composable
private fun AuthCard(title: String, subtitle: String, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .width(760.dx)
            .background(FootraColors.GrayMid)
            .padding(56.dx),
        verticalArrangement = Arrangement.spacedBy(26.dx)
    ) {
        FText(title, FootraFonts.Syne, 48, weight = FontWeight.ExtraBold)
        FText(subtitle, FootraFonts.Poppins, 22, color = FootraColors.Body)
        content()
    }
}

@Composable
private fun SwitchRow(question: String, link: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dx, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FText(question, FootraFonts.Poppins, 20, color = FootraColors.Body)
        FText(
            text = link, font = FootraFonts.Poppins, size = 20, weight = FontWeight.SemiBold,
            underline = true, modifier = Modifier.clickable(onClick = onClick)
        )
    }
}

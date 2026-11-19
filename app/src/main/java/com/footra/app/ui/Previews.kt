package com.footra.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.footra.app.navigation.FootraNavigator
import com.footra.app.ui.screens.AboutScreen
import com.footra.app.ui.screens.ContactScreen
import com.footra.app.ui.screens.HomeScreen
import com.footra.app.ui.screens.LoginScreen
import com.footra.app.ui.screens.ProductScreen
import com.footra.app.ui.screens.SignUpScreen
import com.footra.app.ui.theme.FootraTheme

// Figma frame 1080x2400 px  ==  411x914 dp (Pixel-class phone)

@Composable
private fun rememberPreviewNavigator(): FootraNavigator {
    val controller = rememberNavController()
    return remember(controller) { FootraNavigator(controller) }
}

@Preview(showBackground = true, widthDp = 411, heightDp = 914, name = "Home")
@Composable
private fun HomePreview() = FootraTheme { HomeScreen(rememberPreviewNavigator()) }

@Preview(showBackground = true, widthDp = 411, heightDp = 914, name = "Product")
@Composable
private fun ProductPreview() = FootraTheme { ProductScreen(rememberPreviewNavigator()) }

@Preview(showBackground = true, widthDp = 411, heightDp = 914, name = "About")
@Composable
private fun AboutPreview() = FootraTheme { AboutScreen(rememberPreviewNavigator()) }

@Preview(showBackground = true, widthDp = 411, heightDp = 914, name = "Contact")
@Composable
private fun ContactPreview() = FootraTheme { ContactScreen(rememberPreviewNavigator()) }

@Preview(showBackground = true, widthDp = 411, heightDp = 914, name = "Login")
@Composable
private fun LoginPreview() = FootraTheme { LoginScreen(rememberPreviewNavigator()) }

@Preview(showBackground = true, widthDp = 411, heightDp = 914, name = "Sign Up")
@Composable
private fun SignUpPreview() = FootraTheme { SignUpScreen(rememberPreviewNavigator()) }

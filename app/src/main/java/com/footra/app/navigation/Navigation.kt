package com.footra.app.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.footra.app.ui.screens.AboutScreen
import com.footra.app.ui.screens.ContactScreen
import com.footra.app.ui.screens.HomeScreen
import com.footra.app.ui.screens.LoginScreen
import com.footra.app.ui.screens.ProductScreen
import com.footra.app.ui.screens.SignUpScreen
import com.footra.app.ui.screens.SplashScreen
import com.footra.app.ui.screens.FigmaEaseOut
import com.footra.app.ui.theme.FootraColors

object Routes {
    const val Splash = "splash"
    const val Home = "home"
    const val Product = "product"
    const val About = "about"
    const val Contact = "contact"
    const val Login = "login"
    const val SignUp = "signup"
}

/** All navigation actions from the Figma prototype in one place. */
class FootraNavigator(private val nav: NavHostController) {

    fun fromSplash() = nav.navigate(Routes.Home) {
        popUpTo(Routes.Splash) { inclusive = true }
    }

    fun home() = topLevel(Routes.Home)
    fun product() = topLevel(Routes.Product)
    fun about() = topLevel(Routes.About)
    fun contact() = topLevel(Routes.Contact)

    /** Avatar icon -> Login. Also used by "Login" links (goes back if Login is already on the stack). */
    fun login() {
        if (!nav.popBackStack(Routes.Login, inclusive = false)) {
            nav.navigate(Routes.Login) { launchSingleTop = true }
        }
    }

    fun signUp() = nav.navigate(Routes.SignUp) { launchSingleTop = true }

    private fun topLevel(route: String) = nav.navigate(route) {
        popUpTo(Routes.Home) { inclusive = false }
        launchSingleTop = true
    }
}

@Composable
fun FootraApp() {
    val navController = rememberNavController()
    val navigator = remember(navController) { FootraNavigator(navController) }

    NavHost(
        navController = navController,
        startDestination = Routes.Splash,
        modifier = Modifier.fillMaxSize().background(FootraColors.White),
        enterTransition = { fadeIn(tween(250, easing = FigmaEaseOut)) },
        exitTransition = { fadeOut(tween(250, easing = FigmaEaseOut)) },
        popEnterTransition = { fadeIn(tween(250, easing = FigmaEaseOut)) },
        popExitTransition = { fadeOut(tween(250, easing = FigmaEaseOut)) }
    ) {
        composable(Routes.Splash, exitTransition = { fadeOut(tween(500, easing = FigmaEaseOut)) }) {
            SplashScreen(onFinished = navigator::fromSplash)
        }
        composable(Routes.Home) { HomeScreen(navigator) }
        composable(Routes.Product) { ProductScreen(navigator) }
        composable(Routes.About) { AboutScreen(navigator) }
        composable(Routes.Contact) { ContactScreen(navigator) }
        composable(Routes.Login) { LoginScreen(navigator) }
        composable(Routes.SignUp) { SignUpScreen(navigator) }
    }
}

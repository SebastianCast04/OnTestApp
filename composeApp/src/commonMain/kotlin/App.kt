import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import database.user.UserDao
import presentation.Theme.ThemeApp
import presentation.Theme.colorScheme
import presentation.screens.splash.SplashScreen

@Composable
fun App(userDao: UserDao) {
    ThemeApp {
        Box(
            modifier = Modifier.background(color = colorScheme.inverseSurface).fillMaxSize().windowInsetsPadding(
                WindowInsets.safeDrawing)
        ) {
            Navigator(screen = SplashScreen()) { navigator ->
                SlideTransition(navigator)
            }
        }
    }
}
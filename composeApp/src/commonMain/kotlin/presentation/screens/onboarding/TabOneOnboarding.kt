package presentation.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.Theme.colorScheme
import presentation.screens.login.LoginScreen
import room_cmp.composeapp.generated.resources.Res
import room_cmp.composeapp.generated.resources.try_logo

class TabOneOnboarding : Screen {

    @Composable
    override fun Content() {
        onBoardingContent()
    }

}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun onBoardingContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorScheme.inverseSurface)
    ) {
        val navigator: Navigator? = LocalNavigator.current
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(resource = Res.drawable.try_logo),
                contentDescription = "logo",
                modifier = Modifier.size(290.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                "Try tiendas",
                fontSize = 20.sp,
                color = colorScheme.secondary
            )
            Text(
                text = "Organiza tu negocio y llévalo a el siguiente nivel",
                fontSize = 16.sp,
                color = colorScheme.primary,
                modifier = Modifier.padding(top = 16.dp),
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(60.dp))
            ProgressCircles(activeIndex = 0)
        }
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .clickable {
                    navigator?.replaceAll(item = LoginScreen())
                }
        ) {
            Text(
                text = "Saltar",
                fontSize = 20.sp,
                color = colorScheme.primary,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Flecha",
                tint = colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
@Composable
fun ProgressCircles(activeIndex: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        repeat(3) { index ->
            androidx.compose.foundation.Canvas(modifier = Modifier.size(14.dp)) {
                val color =
                    if (index == activeIndex) colorScheme.secondary else colorScheme.surfaceVariant
                drawCircle(color = color)
            }
        }
    }
}
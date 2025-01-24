package presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.Theme.colorScheme
import presentation.screens.onboarding.OnboardingScreen
import room_cmp.composeapp.generated.resources.Res
import room_cmp.composeapp.generated.resources.logo_login

class SplashScreen : Screen {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {

        val navigator: Navigator? = LocalNavigator.current
        val count = remember { mutableStateOf(0) }

        LaunchedEffect(key1 = true) {
            delay(timeMillis = 2000)
            navigator?.replace(item = OnboardingScreen())
        }
        LaunchedEffect(key1 = true) {
            repeat(Int.MAX_VALUE) {
                for (i in 1..3) {
                    count.value = i
                    delay(timeMillis = 400)
                }
            }
        }

        val colorsA = listOf(
            Color(0xfffefefe),
            Color(0xfffefefe),
        )

        Box(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0XFFc7c7c7))
            )


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(bottom = 20.dp)
                    .shadow(
                        elevation = 26.dp,
                        clip = false
                    )
                    .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
                    .background(Brush.verticalGradient(colorsA))
                    .align(Alignment.TopCenter)
            )

            Column(
                modifier = Modifier.fillMaxSize().padding(top = 280.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    painterResource(resource = Res.drawable.logo_login),
                    contentDescription = "logo",
                    modifier = Modifier.width(281.dp).height(138.dp).offset(y = (-40).dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 500.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Spacer(modifier = Modifier.height(70.dp))
                Text(
                    text = "1.0",
                    fontSize = 16.sp,
                    color = colorScheme.secondary,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(500)
                )
            }
        }
    }

}




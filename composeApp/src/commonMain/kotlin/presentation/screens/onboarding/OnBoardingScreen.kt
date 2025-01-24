package presentation.screens.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.launch
import presentation.Theme.colorScheme
import presentation.screens.login.LoginScreen

class OnboardingScreen: Screen {

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {


        val navigator: Navigator? = LocalNavigator.current
        val pageCount = 3
        val pagerState = rememberPagerState(initialPage = 0) {
            pageCount
        }
        val scope = rememberCoroutineScope()

        Scaffold(
            modifier = Modifier.background(color = colorScheme.primary),
            bottomBar = {


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            scope.launch {
                                val nextPage = pagerState.currentPage + 1
                                if (nextPage < pagerState.pageCount) {
                                    pagerState.animateScrollToPage(nextPage)
                                } else {
                                    navigator?.replaceAll(item = LoginScreen())
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorScheme.primary,
                            contentColor = colorScheme.secondary
                        ),
                        modifier = Modifier.width(350.dp),
                    ) {
                        Text(if (pagerState.currentPage == pageCount - 1) "Ingresar" else "Siguiente")
                    }
                }
            },
            content = {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    when (page) {
                        0 -> TabOneOnboarding().Content()
                        1 -> TabTwoOnboarding().Content()
                        2 -> TabThreeOnboarding().Content()
                    }
                }
            }
        )
    }
}
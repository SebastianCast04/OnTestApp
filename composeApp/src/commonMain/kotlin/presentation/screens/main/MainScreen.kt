package presentation.screens.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import presentation.components.ButtonComponentWithLine
import presentation.components.ButtonTypeWithLine
import presentation.screens.clients.ClientListScreen
import room_cmp.composeapp.generated.resources.Res
import room_cmp.composeapp.generated.resources.logo_login

class MainScreen:Screen {

    @Composable
    override fun Content() {

        MainScreenContent()
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun MainScreenContent(){

    val navigator: Navigator? = LocalNavigator.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val gradientColors = listOf(

            Color(0xFFFFFFFF),
            Color(0xFFFFFFFF),
            Color(0xFFFFFFFF),
            Color(0xFFFFFFFF),
            Color(0xFFFFFFFF),
            Color(0xFFFFFFFF),
            Color(0xFFFFFFFF),
            colorScheme.secondary,
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(480.dp)
                    .offset(y = (-40).dp)
                    .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)),
            ) {
                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    drawRect(
                        brush = Brush.verticalGradient(
                            colors = gradientColors,
                            startY = 0f,
                            endY = size.height
                        )
                    )
                }

                Column (
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Image(
                        painter = painterResource(Res.drawable.logo_login),
                        contentDescription = null,
                        modifier = Modifier.size(120.dp)
                            .clickable{
                            },
                    )
                }

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 460.dp)
                    .background(Color.White)
                    .fillMaxHeight()
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 140.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.fillMaxWidth().height(410.dp))


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Transparent)
                        .offset(y = (-60).dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Transparent)
                            .padding(bottom = 40.dp)
                    ) { }
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Transparent)
                        .offset(y = (-60).dp),
                ) {
                    Text(
                        text = "¿Qué necesitas hacer?",
                        color = colorScheme.secondary,
                        fontWeight = FontWeight(500),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Divider(
                        color = colorScheme.inverseSurface,
                        thickness = 0.8.dp,
                        modifier = Modifier.padding(start = 50.dp, end = 50.dp)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 30.dp, end = 30.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ButtonComponentWithLine(
                            onClick = {
                                navigator?.replace(item = ClientListScreen())
                            },
                            text = "Listado clientes",
                            backgroundColor = colorScheme.primary,
                            borderStroke = BorderStroke(0.5.dp, colorScheme.inverseSurface),
                            textColor = colorScheme.inverseSurface,
                            setFontWeight = FontWeight(600),
                            squareShape = MaterialTheme.shapes.small,
                            buttonType = ButtonTypeWithLine.CircularButton,
                            fontSize = 14.sp,
                            buttonStyle = { modifier ->
                                modifier.padding(0.dp)
                            },
                        )

                        ButtonComponentWithLine(
                            onClick = {
                                navigator?.replace(item = MainScreen())
                            },
                            text = "Creación clientes",
                            backgroundColor = colorScheme.primary,
                            setFontWeight = FontWeight(600),
                            textColor = colorScheme.inverseSurface,
                            borderStroke = BorderStroke(0.5.dp, colorScheme.inverseSurface),
                            squareShape = MaterialTheme.shapes.small,
                            buttonType = ButtonTypeWithLine.CircularButton,
                            fontSize = 14.sp,
                            buttonStyle = { modifier ->
                                modifier.align(Alignment.CenterHorizontally)
                            },
                        )
                    }
                }
            }
        }

        var showWelcome by remember { mutableStateOf(false) }

        LaunchedEffect(key1 = true){
            showWelcome = true
        }



        if (showWelcome) {
            AlertDialog(
                containerColor = colorScheme.primary,
                shape = RoundedCornerShape(6.dp),
                onDismissRequest = {
                    showWelcome = false
                },
                confirmButton = {
                    Button(
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.padding(horizontal = 60.dp),
                        border = BorderStroke(0.5.dp, colorScheme.onSecondary),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorScheme.inverseSurface
                        ),
                        onClick = {
                            showWelcome = false
                        }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Continuar",
                                color = colorScheme.primary
                            )
                        }
                    }
                },
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painterResource(Res.drawable.logo_login),
                            contentDescription = "Error",
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(modifier = Modifier.height(26.dp))
                        Text(
                            text = "¡Bienvenid@ a la familia OnOff!",
                            color = colorScheme.secondary,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                text = {
                    Text(
                        text = "Nos alegra que estes de vuelta con nosotros",
                        color = colorScheme.surface,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(400),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }
    }
}
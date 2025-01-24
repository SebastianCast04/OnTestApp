package presentation.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import domain.util.LoginState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform
import org.koin.mp.KoinPlatform.getKoin
import presentation.Theme.colorScheme
import presentation.components.ButtonComponent
import presentation.components.ButtonComponentWithLine
import presentation.components.ButtonType
import presentation.components.ButtonTypeWithLine
import presentation.components.SimpleTextInputComponent
import presentation.screens.main.MainScreen
import presentation.screens.splash.SplashScreen
import room_cmp.composeapp.generated.resources.Res
import room_cmp.composeapp.generated.resources.cancel_new
import room_cmp.composeapp.generated.resources.logo_login
import room_cmp.composeapp.generated.resources.logo_password
import room_cmp.composeapp.generated.resources.mail_outline

class LoginScreen : Screen {
    @OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val loginViewModel = rememberScreenModel {
            LoginViewModel(
                loginUseCase = getKoin().get(),
            )
        }

        val stateLogin by loginViewModel.loginState.collectAsState()
        val navigator: Navigator? = LocalNavigator.current
        var showErrorDialog by remember { mutableStateOf(false) }



        LaunchedEffect(stateLogin){
            when (stateLogin) {

                is LoginState.Success -> {
                    navigator?.replace(item = MainScreen())
                }
                is LoginState.Error -> {
                    showErrorDialog = true
                }
                else -> {}
            }
        }

        if (showErrorDialog) {
            AlertDialog(
                properties = DialogProperties(usePlatformDefaultWidth = false),
                modifier = Modifier.width(360.dp).height(290.dp),
                containerColor = colorScheme.primary,
                shape = RoundedCornerShape(6.dp),
                onDismissRequest = {
                    showErrorDialog = false
                },
                confirmButton = {
                    Button(
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.padding(horizontal = 80.dp).padding(top = 16.dp),
                        border = BorderStroke(0.5.dp, colorScheme.onSecondary),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorScheme.onSecondary
                        ),
                        onClick = {
                            showErrorDialog = false
                        }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Aceptar",
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
                            painterResource(resource = Res.drawable.cancel_new),
                            contentDescription = "Error",
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = "¡Lo sentimos!",
                            color = colorScheme.error,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                text = {
                    Text(
                        text = "Contraseña y/o correo incorrectos",
                        color = colorScheme.surface,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }


        LoginScreenContent(
            onLoginClicked = { email, password ->
                loginViewModel.login(email = email, password = password)
            }
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginScreenContent(
    onLoginClicked: (String, String) -> Unit,
){
    var email by remember { mutableStateOf(value= "") }
    var password by remember { mutableStateOf(value= "") }
    Column(
        modifier = Modifier.fillMaxSize().background(color = colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp),
            shape = RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp),
            color = Color.White,
            shadowElevation = 70.dp,
            tonalElevation = 8.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp))
                    .background(
                        color = colorScheme.primary
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.logo_login),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(100.dp)
                )
            }
        }



        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Iniciar sesión",
            color = colorScheme.secondary,
            fontSize = 24.sp,
        )

        Spacer(modifier = Modifier.height(30.dp))

        Column (
            modifier = Modifier.fillMaxWidth().padding(start = 32.dp).offset(y = 14.dp),
            horizontalAlignment = Alignment.Start,
        ){
            Text(
                text = "Correo electrónico",
                color = colorScheme.inverseSurface,
                fontSize = 14.sp,
                fontWeight = FontWeight(400)
            )
        }


        Row(modifier = Modifier.fillMaxWidth().padding(start = 32.dp, end = 26.dp).offset(y = (-6).dp)) {

            Image(
                painter = painterResource(resource = Res.drawable.mail_outline),
                contentDescription = "Mail Icon",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(20.dp)
                    .offset(y = (32).dp)
            )
            Spacer(modifier = Modifier.width(2.dp))

            SimpleTextInputComponent(
                value = email,
                onValueChange = { email = it },
                placeHolder = "Correo electronico",
                //isError = stateLogin.error,
            )
        }

        Column (
            modifier = Modifier.fillMaxWidth().padding(start = 32.dp).offset(y = 14.dp),
            horizontalAlignment = Alignment.Start,
        ){
            Text(
                text = "Contraseña",
                fontSize = 14.sp,
                fontWeight = FontWeight(400)
            )
        }
        Row(modifier = Modifier.fillMaxWidth().padding(start = 32.dp, end = 26.dp).offset(y = (-6).dp)) {

            Image(
                painter = painterResource(resource = Res.drawable.logo_password),
                contentDescription = "Password Icon",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(20.dp)
                    .offset(y = (32).dp)
            )

            SimpleTextInputComponent(
                value = password,
                onValueChange = { password = it },
                placeHolder = "Contraseña",
                //icon = Icons.Default.VisibilityOff,
                isPassword = true,
                //isError = stateLogin.error,
                iconColor = colorScheme.inverseSurface
            )
        }

        Spacer(modifier = Modifier.height(54.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            ButtonComponent(
                onClick = {
                    //showRememberPassword.invoke()
                },
                text = "Olvidaste tu contraseña?",
                textColor = colorScheme.secondary,
                buttonType = ButtonType.TextButton,
                fontSize = 15.sp,
                horizontalArrangement = Arrangement.End,
                buttonStyle = { modifier ->
                    modifier.fillMaxWidth().padding(start = 184.dp)
                },
                setFontWeight = FontWeight(500)
            )
        }
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)) {
            ButtonComponentWithLine(
                onClick = {
                    onLoginClicked(email, password)
                },
                text ="Ingresar",
                backgroundColor = colorScheme.inverseSurface,
                textColor = colorScheme.primary,
                buttonType = ButtonTypeWithLine.CircularButton,
                horizontalArrangement = Arrangement.Center,
                buttonStyle = { modifier ->
                    modifier.fillMaxWidth().padding(horizontal = 32.dp)
                },
            )
        }
        Spacer(modifier = Modifier.fillMaxWidth().height(18.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 32.dp, end = 32.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "No tienes una cuenta?",
                color = colorScheme.inverseSurface,
                fontSize = 16.sp
            )
            ButtonComponent(
                onClick = {
                },
                text = "Registrate",
                textColor = colorScheme.secondary,
                buttonType = ButtonType.TextButton,
                fontSize = 20.sp,
                setFontWeight = FontWeight(580)
            )
        }
        Text(
            text = "1.0",
            color = colorScheme.secondary,
            fontWeight = FontWeight(500),
            modifier = Modifier.padding(
                PaddingValues(top = 4.dp)
            ),
            fontSize = 16.sp
        )
    }
}

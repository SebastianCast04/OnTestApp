package presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import room_cmp.composeapp.generated.resources.Res
import room_cmp.composeapp.generated.resources.compose_multiplatform
import room_cmp.composeapp.generated.resources.hide_password
import room_cmp.composeapp.generated.resources.show_password

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SimpleTextInputComponent(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    isPassword: Boolean = false,
    isError: Boolean = false,
    textError: String = "",
    icon: ImageVector? = null,
    iconColor: Color = Color(0xFF61B22F),
    sizeIcon: Dp? = 24.dp,
    onImeAction: () -> Unit = {},
    onFocus: ((Boolean) -> Unit)? = null,
) {
    var passwordVisibility by remember { mutableStateOf(isPassword) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                //top = 30.dp, bottom = 12.dp
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp)
                        .onFocusChanged { focusState ->
                            onFocus?.invoke(focusState.isFocused)
                        }
                        .offset(y = 30.dp),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    visualTransformation = if (isPassword && passwordVisibility) {
                        PasswordVisualTransformation()
                    } else {
                        VisualTransformation.None
                    },
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if (value.isEmpty()) {
                                Text(
                                    text = placeHolder,
                                    color = Color.Gray,
                                    fontSize = 16.sp
                                )
                            }
                            innerTextField()
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            onImeAction()
                        }
                    )
                )

                Spacer(modifier = Modifier.height(42.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(if (isError) Color.Red else Color.DarkGray)

                )
            }

            if (isPassword && icon != null) {
                IconButton(
                    onClick = { passwordVisibility = !passwordVisibility },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(y = 10.dp)
                        .size(sizeIcon ?: 18.dp)
                ) {
                    Image(
                        painter = painterResource(if (passwordVisibility) Res.drawable.hide_password else Res.drawable.show_password),
                        contentDescription = if (passwordVisibility) "Ocultar contraseña" else "Mostrar contraseña",
                    )
                }
            }
        }

        if (isError) {
            Text(
                text = textError,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}
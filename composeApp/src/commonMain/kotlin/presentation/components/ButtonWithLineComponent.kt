package presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.Theme.colorScheme

@Composable
fun ButtonComponentWithLine(
    text: String,
    icon: ImageVector? = null,
    iconPosition: RightLeftEnum = RightLeftEnum.LEFT,
    iconColor: Color = colorScheme.error,
    sizeIcon: Dp? = 24.dp,
    setFontWeight: FontWeight? = null,
    textColor: Color = colorScheme.inverseSurface,
    backgroundColor: Color = colorScheme.error,
    disabled: Boolean = false,
    buttonType: ButtonTypeWithLine = ButtonTypeWithLine.ButtonWithLine,
    fontSize: TextUnit = 16.sp,
    borderStroke: BorderStroke? = null,
    defaultText: String? = "",
    buttonStyle: (Modifier) -> Modifier = { it },
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    squareShape: Shape = MaterialTheme.shapes.small,
    onClick: () -> Unit = {},

    ) {

    val displayText = if (text.isEmpty()) defaultText else text

    when (buttonType) {

        ButtonTypeWithLine.ButtonWithLine -> Box(
            modifier = Modifier
                .clickable(enabled = !disabled, onClick = onClick)
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Column {
                ButtonContentWithLine(
                    text = displayText ?: "",
                    icon = icon,
                    iconPosition = RightLeftEnum.RIGHT,
                    iconColor = iconColor,
                    sizeIcon = sizeIcon,
                    textColor = textColor,
                    fontSize = fontSize,
                    setFontWeight = setFontWeight,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = buttonStyle,

                    )
                Divider(color = backgroundColor, thickness = 1.dp)
            }
        }

        ButtonTypeWithLine.CircularButton -> ElevatedButton(
            onClick = onClick,
            enabled = !disabled,
            border = borderStroke,
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (disabled) Color(0XFFC8C8C8) else backgroundColor,
                disabledContainerColor = Color(0XFFC8C8C8)
            ),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ButtonContentWithLine(
                    text = displayText ?: "",
                    icon = icon,
                    iconPosition = iconPosition,
                    sizeIcon = sizeIcon,
                    textColor = textColor,
                    fontSize = fontSize,
                    setFontWeight = setFontWeight,
                    horizontalArrangement = Arrangement.Center,
                    modifier = buttonStyle
                )
            }
        }
    }
}

@Composable
private fun ButtonContentWithLine(
    text: String,
    icon: ImageVector?,
    iconPosition: RightLeftEnum = RightLeftEnum.RIGHT,
    sizeIcon: Dp? = 28.dp,
    iconColor: Color = colorScheme.onSecondary,
    textColor: Color,
    fontSize: TextUnit,
    setFontWeight: FontWeight? = null,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    modifier: (Modifier) -> Modifier = { it },
) {
    Row(
        modifier = modifier(Modifier.fillMaxWidth()),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null && iconPosition == RightLeftEnum.LEFT) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = sizeIcon?.let { Modifier.size(it) } ?: Modifier,

                )
        }

        Text(
            text = text,
            color = textColor,
            fontSize = fontSize,
            textAlign = TextAlign.Center,
            fontWeight = setFontWeight
        )

        if (icon != null && iconPosition == RightLeftEnum.RIGHT) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = sizeIcon?.let { Modifier.size(it) } ?: Modifier
            )
        }
    }
}

enum class ButtonTypeWithLine {
    ButtonWithLine,
    CircularButton
}
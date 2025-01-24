package presentation.Theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun ThemeApp(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = colorScheme,
        //typography = typography(),
        shapes = MaterialTheme.shapes,
        content = {
            content()
        })
}
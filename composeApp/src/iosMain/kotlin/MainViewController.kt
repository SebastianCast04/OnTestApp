import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import database.getUserDataBase

fun MainViewController() = ComposeUIViewController {
    val dao = remember {
        getUserDataBase().userDao()
    }
    App(dao)
}
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ui.color.ColorScreen
import ui.navigation.NavScreen
import ui.navigation.NavViewModel
import ui.navigation.Navigation
import ui.page.PageScreen
import ui.text.TextScreen

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Beep 관리자 프로그램",
        state = rememberWindowState(width = 1600.dp, height = 900.dp),
    ) {
        val navViewModel = NavViewModel()

        MaterialTheme {
            Row {
                NavScreen {
                    navViewModel.page.value = it
                }
                when (navViewModel.page.value) {
                    Navigation.PAGE -> PageScreen()
                    Navigation.COLOR -> ColorScreen()
                    Navigation.TEXT -> TextScreen()
                }
            }
        }
    }
}

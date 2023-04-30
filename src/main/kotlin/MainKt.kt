import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ui.screen.ScreenState
import ui.screen.color.ColorScreen
import ui.screen.navigation.NavScreen
import ui.screen.navigation.NavViewModel
import ui.screen.navigation.Navigation
import ui.screen.page.PageScreen
import ui.screen.page.PageState
import ui.screen.text.TextScreen

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Beep 관리자 프로그램",
        state = rememberWindowState(width = 1600.dp, height = 900.dp),
    ) {
        val navViewModel = NavViewModel()
        val screenState = ScreenState()
        val pageState = PageState()

        MaterialTheme {
            Row {
                NavScreen {
                    navViewModel.page.value = it
                }
                when (navViewModel.page.value) {
                    Navigation.PAGE -> PageScreen(screenState, pageState)
                    Navigation.COLOR -> ColorScreen(screenState)
                    Navigation.TEXT -> TextScreen(screenState)
                }
            }
        }
    }
}

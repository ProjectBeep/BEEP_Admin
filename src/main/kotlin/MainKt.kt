import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            Column {
                Row(modifier = Modifier.weight(1f)) {
                    NavScreen {
                        navViewModel.page.value = it
                    }
                    when (navViewModel.page.value) {
                        Navigation.PAGE -> PageScreen(screenState, pageState)
                        Navigation.COLOR -> ColorScreen(screenState)
                        Navigation.TEXT -> TextScreen(screenState)
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .height(48.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                ) {
                    Button(onClick = {}) {
                        Text(text = "리소스 적용")
                    }
                    Button(onClick = {}) {
                        Text(text = "APK 생성")
                    }
                }
            }
        }
    }
}

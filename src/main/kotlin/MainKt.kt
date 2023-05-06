import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import core.Compose
import ui.common.utils.openBrowse
import ui.designsystem.draganddrop.WindowDropTarget
import ui.screen.ScreenState
import ui.screen.color.ColorScreen
import ui.screen.color.ColorScreenState
import ui.screen.font.FontScreen
import ui.screen.font.FontScreenState
import ui.screen.image.ImageScreen
import ui.screen.image.ImageScreenState
import ui.screen.navigation.NavScreen
import ui.screen.navigation.NavState
import ui.screen.navigation.Navigation
import ui.screen.page.PageScreen
import ui.screen.page.PageState
import ui.screen.page.PageViewModel
import ui.screen.text.TextScreen
import ui.screen.text.TextScreenState

fun main() = application {
    val navState = NavState()
    val screenState = ScreenState()
    val pageState = PageState()
    val imageScreenState = ImageScreenState()
    val colorScreenState = ColorScreenState()
    val fontScreenState = FontScreenState()
    val textScreenState = TextScreenState()
    val pageViewModel = PageViewModel()
    pageState.listState.changeList(
        pageViewModel.pageList.value,
    )

    val windowDropTarget = WindowDropTarget(
        listOf(
            pageState.editorState.dragAndDropState,
        ),
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "Beep 관리자 프로그램",
        state = rememberWindowState(width = 1600.dp, height = 900.dp),
    ) {
        val density = LocalDensity.current
        LaunchedEffect(key1 = null) {
            Compose.density = density
        }

        window.contentPane.dropTarget = windowDropTarget

        MaterialTheme {
            Column {
                Row(modifier = Modifier.weight(1f)) {
                    NavScreen(
                        navState.page.value,
                    ) {
                        navState.page.value = it
                    }
                    when (navState.page.value) {
                        Navigation.PAGE -> PageScreen(
                            screenState,
                            pageState,
                            onSchemeClick = {
                                navState.page.value = it.navigation
                                screenState.selectModel(it.model)
                            },
                            onLinkClick = {
                                openBrowse(it)
                            },
                        )

                        Navigation.IMAGE -> ImageScreen(
                            pageViewModel.pageList.value,
                            screenState,
                            imageScreenState,
                        )

                        Navigation.COLOR -> ColorScreen(
                            pageViewModel.pageList.value,
                            screenState,
                            colorScreenState,
                        )

                        Navigation.FONT -> FontScreen(
                            pageViewModel.pageList.value,
                            screenState,
                            fontScreenState,
                        )

                        Navigation.TEXT -> TextScreen(
                            pageViewModel.pageList.value,
                            screenState,
                            textScreenState,
                        )
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

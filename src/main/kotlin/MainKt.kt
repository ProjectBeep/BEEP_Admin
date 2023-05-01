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
import ui.screen.ScreenState
import ui.screen.color.ColorScreen
import ui.screen.navigation.NavScreen
import ui.screen.navigation.NavState
import ui.screen.navigation.Navigation
import ui.screen.page.PageScreen
import ui.screen.page.PageState
import ui.screen.page.PageViewModel
import ui.screen.text.TextScreen
import java.awt.datatransfer.DataFlavor
import java.awt.dnd.DnDConstants
import java.awt.dnd.DropTarget
import java.awt.dnd.DropTargetDragEvent
import java.awt.dnd.DropTargetDropEvent
import java.io.File

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Beep 관리자 프로그램",
        state = rememberWindowState(width = 1600.dp, height = 900.dp),
    ) {
        val navState = NavState()
        val screenState = ScreenState()
        val pageState = PageState()
        val pageViewModel = PageViewModel()

        val density = LocalDensity.current
        LaunchedEffect(key1 = null) {
            Compose.density = density
        }

        window.contentPane.dropTarget = object : DropTarget() {
            private val allowExt = setOf("jpg", "png", "webp", "xml", "svg")

            override fun dragOver(dtde: DropTargetDragEvent?) {
                dtde ?: return
                if (pageState.isDropOver(dtde.location)) {
                    val dragFiles = dtde.transferable?.getTransferData(DataFlavor.javaFileListFlavor) as? List<File>
                    val files = dragFiles?.filter { it.extension in allowExt }
                    pageState.dropAllow.value = files?.isNotEmpty() ?: false
                }
            }

            override fun drop(dtde: DropTargetDropEvent?) {
                pageState.dragOver.value = false

                dtde ?: return
                if (pageState.dropAllow.value) {
                    pageState.dropAllow.value = false
                    dtde.acceptDrop(DnDConstants.ACTION_REFERENCE)
                    val dropFiles = dtde.transferable?.getTransferData(DataFlavor.javaFileListFlavor) as? List<File>
                    pageState.dropFile.value = dropFiles?.first { it.extension in allowExt } ?: return
                }
            }
        }

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
                            pageViewModel.pageList.value,
                            screenState,
                            pageState,
                        )

                        Navigation.COLOR -> ColorScreen(
                            pageViewModel.pageList.value,
                            screenState,
                        )

                        Navigation.TEXT -> TextScreen(
                            pageViewModel.pageList.value,
                            screenState,
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

package ui.screen.image

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import model.PageModel
import ui.designsystem.screenmenu.ScreenMenu
import ui.screen.ScreenState

@Composable
fun ImageScreen(
    pageList: List<PageModel>,
    screenState: ScreenState,
    imageScreenState: ImageScreenState,
) {
    ImageToolbar(
        pageList = pageList,
        current = screenState.selectedModel.value,
        onPageChange = {
            screenState.selectModel(it)
        },
    )
}

@Composable
fun ImageToolbar(
    pageList: List<PageModel>,
    current: PageModel?,
    onPageChange: (PageModel) -> Unit,
) {
    Row {
        ScreenMenu(pageList, current, onPageChange)
    }
}

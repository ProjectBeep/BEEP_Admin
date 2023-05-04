package ui.screen.font

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import model.PageModel
import ui.designsystem.screenmenu.ScreenMenu
import ui.screen.ScreenState

@Composable
fun FontScreen(
    pageList: List<PageModel>,
    screenState: ScreenState,
    fontScreenState: FontScreenState,
) {
    FontToolbar(
        pageList = pageList,
        current = screenState.selectedModel.value,
        onPageChange = {
            screenState.selectModel(it)
        },
    )
}

@Composable
fun FontToolbar(
    pageList: List<PageModel>,
    current: PageModel?,
    onPageChange: (PageModel) -> Unit,
) {
    Row {
        ScreenMenu(pageList, current, onPageChange)
    }
}

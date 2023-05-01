package ui.screen.text

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import model.PageModel
import ui.designsystem.screenmenu.ScreenMenu
import ui.screen.ScreenState

@Composable
fun TextScreen(
    pageList: List<PageModel>,
    screenState: ScreenState,
    textScreenState: TextScreenState,
) {
    TextToolbar(
        pageList = pageList,
        current = screenState.selectedModel.value,
        onPageChange = {
            screenState.selectModel(it)
        },
    )
}

@Composable
fun TextToolbar(
    pageList: List<PageModel>,
    current: PageModel?,
    onPageChange: (PageModel) -> Unit,
) {
    Row {
        ScreenMenu(pageList, current, onPageChange)
    }
}

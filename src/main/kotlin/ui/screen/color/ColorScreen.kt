package ui.screen.color

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import model.PageModel
import ui.designsystem.screenmenu.ScreenMenu
import ui.screen.ScreenState

@Composable
fun ColorScreen(
    pageList: List<PageModel>,
    screenState: ScreenState,
    colorScreenState: ColorScreenState,
) {
    ColorToolbar(
        pageList = pageList,
        current = screenState.selectedModel.value,
        onPageChange = {
            screenState.selectModel(it)
        },
    )
}

@Composable
fun ColorToolbar(
    pageList: List<PageModel>,
    current: PageModel?,
    onPageChange: (PageModel) -> Unit,
) {
    Row {
        ScreenMenu(pageList, current, onPageChange)
    }
}

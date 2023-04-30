package ui.screen.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import ui.screen.ScreenState

@Composable
fun PageScreen(
    screenState: ScreenState,
    pageState: PageState,
) {
    val viewModel = remember { PageViewModel() }

    Column {
        PageToolbar(
            pageState.showEdit.value,
            onShowEditToggle = {
                pageState.changeShowEdit(it)
            },
        )
        Row {
            PageListScreen(
                modifier = Modifier.weight(1f).clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) {
                    screenState.selectModel(null)
                },
                list = viewModel.pageList.value,
                state = pageState.lazyGridScrollState,
                selectedModel = screenState.selectedModel.value,
                onSelectModel = {
                    screenState.selectModel(it)
                },
            )
            if (pageState.showEdit.value) {
                PageEditorScreen(
                    model = screenState.selectedModel.value,
                    onChangeDropRect = { pageState.changeDropRange(it) },
                )
            }
        }
    }
}

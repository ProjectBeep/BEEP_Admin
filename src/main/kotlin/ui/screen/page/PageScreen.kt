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
    onSchemeClick: (PageScheme) -> Unit,
    onLinkClick: (String) -> Unit,
) {
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
                    pageState.editorState.selectModel(null)
                    screenState.selectModel(null)
                },
                state = pageState.listState,
                selectedModel = screenState.selectedModel.value,
                onSelectModel = {
                    pageState.editorState.selectModel(null)
                    screenState.selectModel(it)
                },
                onSchemeClick = onSchemeClick,
                onLinkClick = onLinkClick,
            )
            if (pageState.showEdit.value) {
                PageEditorScreen(
                    state = pageState.editorState,
                    model = screenState.selectedModel.value,
                )
            }
        }
    }
}

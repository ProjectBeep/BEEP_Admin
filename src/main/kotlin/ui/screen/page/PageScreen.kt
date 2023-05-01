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
                    pageState.selectModel(null)
                    screenState.selectModel(null)
                },
                list = viewModel.pageList.value,
                state = pageState.lazyGridScrollState,
                selectedModel = screenState.selectedModel.value,
                onSelectModel = {
                    pageState.selectModel(it)
                    screenState.selectModel(it)
                },
            )
            if (pageState.showEdit.value) {
                PageEditorScreen(
                    scrollState = pageState.editorScrollState,
                    model = screenState.selectedModel.value,
                    isDragOver = pageState.dragOver.value,
                    isDropAllow = pageState.dropAllow.value,
                    tempFile = pageState.dropFile.value,
                    displayNameValue = pageState.editDisplayNameValue.value,
                    dirValue = pageState.editDirValue.value,
                    figmaUrlValue = pageState.editFigmaUrlValue.value,
                    zeplinUrlValue = pageState.editZeplinUrlValue.value,
                    wikiUrlValue = pageState.editWikiUrlValue.value,
                    onChangeDropRect = { pageState.changeDropRect(it) },
                    onDisplayNameChange = {
                        pageState.editDisplayNameValue.value = it
                    },
                    onDirChange = {
                        pageState.editDirValue.value = it
                    },
                    onFigmaUrlChange = {
                        pageState.editFigmaUrlValue.value = it
                    },
                    onZeplinUrlChange = {
                        pageState.editZeplinUrlValue.value = it
                    },
                    onWikiUrlChange = {
                        pageState.editWikiUrlValue.value = it
                    },
                )
            }
        }
    }
}

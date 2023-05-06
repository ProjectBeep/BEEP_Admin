package ui.screen.page

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.mutableStateOf
import model.PageModel
import ui.designsystem.draganddrop.DragAndDropState

class PageEditorState {
    val scrollState = ScrollState(initial = 0)

    val dragAndDropState = DragAndDropState(setOf("jpg", "png", "webp", "xml", "svg"))

    val displayName = mutableStateOf("")

    val dir = mutableStateOf("")

    val figmaUrl = mutableStateOf("")

    val zeplinUrl = mutableStateOf("")

    val wikiUrl = mutableStateOf("")

    fun selectModel(model: PageModel?) {
        dragAndDropState.dropFiles(emptyList())
        displayName.value = model?.displayName ?: ""
        dir.value = model?.dir ?: ""
        figmaUrl.value = model?.figmaUrl ?: ""
        zeplinUrl.value = model?.zeplinUrl ?: ""
        wikiUrl.value = model?.wikiUrl ?: ""
    }
}

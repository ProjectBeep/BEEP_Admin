package ui.screen.page

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.mutableStateOf
import model.PageModel
import ui.common.state.LazyGridScrollState
import ui.designsystem.draganddrop.DragAndDropState

class PageState(
    val editorDragAndDropState: DragAndDropState,
) {
    val lazyGridScrollState = LazyGridScrollState()

    val editorScrollState = ScrollState(initial = 0)

    val showEdit = mutableStateOf(false)

    fun changeShowEdit(value: Boolean) {
        showEdit.value = value
    }

//    private val dropRect = mutableStateOf(Rect.Zero)
//
//    val dragOver = mutableStateOf(false)
//
//    val dropAllow = mutableStateOf(false)
//
//    val dropFile = mutableStateOf<File?>(null)
//
//    fun changeDropRect(rect: Rect) {
//        dropRect.value = rect
//    }
//
//    fun isDropOver(pos: Point): Boolean {
//        val rect = dropRect.value
//        val over = pos.x.toFloat() in rect.left..rect.right &&
//            pos.y.toFloat() in rect.top..rect.bottom
//        dragOver.value = over
//        if (!over) {
//            dropAllow.value = false
//        }
//        return over
//    }

    val editDisplayNameValue = mutableStateOf("")

    val editDirValue = mutableStateOf("")

    val editFigmaUrlValue = mutableStateOf("")

    val editZeplinUrlValue = mutableStateOf("")

    val editWikiUrlValue = mutableStateOf("")

    fun selectModel(model: PageModel?) {
        editorDragAndDropState.dropFiles(emptyList())
        editDisplayNameValue.value = model?.displayName ?: ""
        editDirValue.value = model?.dir ?: ""
        editFigmaUrlValue.value = model?.figmaUrl ?: ""
        editZeplinUrlValue.value = model?.zeplinUrl ?: ""
        editWikiUrlValue.value = model?.wikiUrl ?: ""
    }
}

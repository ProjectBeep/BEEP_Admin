package ui.screen.page

import androidx.compose.runtime.mutableStateOf

class PageState {

    val listState = PageListState()

    val editorState = PageEditorState()

    val showEdit = mutableStateOf(false)

    fun changeShowEdit(value: Boolean) {
        showEdit.value = value
    }
}

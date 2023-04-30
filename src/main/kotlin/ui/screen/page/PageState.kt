package ui.screen.page

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Rect
import ui.common.state.LazyGridScrollState

class PageState {
    val lazyGridScrollState = LazyGridScrollState()

    val showEdit = mutableStateOf(true)

    fun changeShowEdit(value: Boolean) {
        showEdit.value = value
    }

    val dropRect = mutableStateOf(Rect.Zero)

    fun changeDropRange(rect: Rect) {
        dropRect.value = rect
    }
}

package ui.screen.page

import androidx.compose.runtime.mutableStateOf
import ui.common.state.LazyGridScrollState

class PageState {
    val lazyGridScrollState = LazyGridScrollState()

    val showEdit = mutableStateOf(true)

    fun changeShowEdit(value: Boolean) {
        showEdit.value = value
    }
}

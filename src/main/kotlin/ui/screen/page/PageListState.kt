package ui.screen.page

import androidx.compose.runtime.mutableStateOf
import model.PageModel
import ui.common.state.LazyGridScrollState

class PageListState {
    val lazyGridScrollState = LazyGridScrollState()

    val pageList = mutableStateOf<List<PageModel>>(emptyList())

    fun changeList(list: List<PageModel>) {
        pageList.value = list
    }
}

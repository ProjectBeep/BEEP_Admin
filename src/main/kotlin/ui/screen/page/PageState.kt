package ui.screen.page

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Rect
import ui.common.state.LazyGridScrollState
import java.awt.Point
import java.io.File

class PageState {
    val lazyGridScrollState = LazyGridScrollState()

    val showEdit = mutableStateOf(true)

    fun changeShowEdit(value: Boolean) {
        showEdit.value = value
    }

    private val dropRect = mutableStateOf(Rect.Zero)

    val dragOver = mutableStateOf(false)

    val dropAllow = mutableStateOf(false)

    val dropFile = mutableStateOf<File?>(null)

    fun changeDropRect(rect: Rect) {
        dropRect.value = rect
    }

    fun isDropOver(pos: Point): Boolean {
        val rect = dropRect.value
        val over = pos.x.toFloat() in rect.left..rect.right &&
            pos.y.toFloat() in rect.top..rect.bottom
        dragOver.value = over
        if (!over) {
            dropAllow.value = false
        }
        return over
    }
}

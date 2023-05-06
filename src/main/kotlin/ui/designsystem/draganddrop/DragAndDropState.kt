package ui.designsystem.draganddrop

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Rect
import java.awt.Point
import java.io.File

class DragAndDropState(
    private val dropAllowExtension: Set<String> = emptySet(),
) {
    private val dropRect = mutableStateOf(Rect.Zero)

    fun changeDropRect(rect: Rect) {
        dropRect.value = rect
    }

    val isDragOver = mutableStateOf(false)

    fun dragOver(pos: Point, files: List<File>) {
        val rect = dropRect.value
        val over = rect != Rect.Zero &&
            pos.x.toFloat() in rect.left..rect.right &&
            pos.y.toFloat() in rect.top..rect.bottom
        isDragOver.value = over
        isDropAllow.value = over && files.firstOrNull {
            it.extension in dropAllowExtension
        } != null
    }

    val isDropAllow = mutableStateOf(false)

    val droppedFiles = mutableStateOf<List<File>>(emptyList())

    val firstDroppedFile = mutableStateOf<File?>(null)

    fun dropFiles(list: List<File>) {
        droppedFiles.value = list.filter { it.extension in dropAllowExtension }
        firstDroppedFile.value = droppedFiles.value.firstOrNull()
    }
}

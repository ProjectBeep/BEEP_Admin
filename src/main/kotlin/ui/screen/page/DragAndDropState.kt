package ui.screen.page

sealed class DragAndDropState {

    data class Drag(
        val test: Boolean,
    ) : DragAndDropState()
}

package ui.screen.image

import androidx.compose.foundation.ScrollState
import ui.designsystem.draganddrop.DragAndDropState

class ImageScreenState {

    val colorScrollState = ScrollState(initial = 0)

    val dragAndDropState = DragAndDropState(setOf("jpg", "png", "webp", "xml", "svg"))
}

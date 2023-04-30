package ui.designsystem.asyncimage.formatter

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import ui.designsystem.asyncimage.model.ImageModel

interface ImageFormatter<in T : ImageModel> {

    @Composable
    fun painterFor(image: T): Painter
}

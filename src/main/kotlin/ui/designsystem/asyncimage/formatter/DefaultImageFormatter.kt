package ui.designsystem.asyncimage.formatter

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import ui.designsystem.asyncimage.model.ImageModel

class DefaultImageFormatter : ImageFormatter<ImageModel.Default> {

    @Composable
    override fun painterFor(image: ImageModel.Default): Painter {
        return BitmapPainter(image.imageBitmap)
    }
}

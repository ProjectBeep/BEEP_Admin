package ui.designsystem.asyncimage.formatter

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import ui.designsystem.asyncimage.model.ImageModel

class XmlImageFormatter : ImageFormatter<ImageModel.Xml> {

    @Composable
    override fun painterFor(image: ImageModel.Xml): Painter {
        return rememberVectorPainter(image.imageVector)
    }
}

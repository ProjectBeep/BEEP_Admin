package ui.designsystem.asyncimage.formatter

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import ui.designsystem.asyncimage.model.ImageModel

class SvgImageFormatter : ImageFormatter<ImageModel.Svg> {

    @Composable
    override fun painterFor(image: ImageModel.Svg): Painter {
        return image.painter
    }
}

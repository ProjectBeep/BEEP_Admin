package ui.designsystem.asyncimage.loader

import androidx.compose.ui.res.loadSvgPainter
import core.Compose
import ui.designsystem.asyncimage.model.ImageModel
import java.io.InputStream

class SvgImageLoader : ImageLoader<ImageModel.Svg>() {

    override fun loadLocal(inputStream: InputStream): ImageModel.Svg {
        val painter = loadSvgPainter(inputStream, Compose.density)
        return ImageModel.Svg(painter)
    }
}

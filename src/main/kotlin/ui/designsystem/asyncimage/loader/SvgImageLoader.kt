package ui.designsystem.asyncimage.loader

import androidx.compose.ui.res.loadSvgPainter
import theme.Compose
import ui.designsystem.asyncimage.model.ImageModel
import java.io.InputStream

class SvgImageLoader : ImageLoader<ImageModel.Svg>() {

    override fun loadDiskCache(inputStream: InputStream): ImageModel.Svg {
        val painter = loadSvgPainter(inputStream, Compose.density)
        return ImageModel.Svg(painter)
    }
}

package ui.designsystem.asyncimage.loader

import androidx.compose.ui.res.loadImageBitmap
import ui.designsystem.asyncimage.model.ImageModel
import java.io.InputStream

class DefaultImageLoader : ImageLoader<ImageModel.Default>() {

    override fun loadLocal(inputStream: InputStream): ImageModel.Default {
        val imageBitmap = loadImageBitmap(inputStream)
        return ImageModel.Default(imageBitmap)
    }
}

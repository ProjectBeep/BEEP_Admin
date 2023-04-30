package ui.designsystem.asyncimage

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import ui.designsystem.asyncimage.formatter.DefaultImageFormatter
import ui.designsystem.asyncimage.formatter.SvgImageFormatter
import ui.designsystem.asyncimage.formatter.XmlImageFormatter
import ui.designsystem.asyncimage.loader.DefaultImageLoader
import ui.designsystem.asyncimage.loader.SvgImageLoader
import ui.designsystem.asyncimage.loader.XmlImageLoader
import ui.designsystem.asyncimage.model.ImageModel

object AsyncImageCache {

    private val memoryCache = LruCache<String, ImageModel>(30)

    fun getImage(url: String): ImageState {
        return try {
            val memory = memoryCache.get(url)
            if (memory != null) {
                return ImageState.Success(memory)
            }
            val loader = getImageLoader(url)
            val image = loader.loadImage(url).also {
                memoryCache.put(url, it)
            }
            ImageState.Success(image)
        } catch (e: Exception) {
            ImageState.Failed(e)
        }
    }

    @Composable
    fun painterFor(imageModel: ImageModel): Painter {
        return when (imageModel) {
            is ImageModel.Svg -> SvgImageFormatter().painterFor(imageModel)
            is ImageModel.Xml -> XmlImageFormatter().painterFor(imageModel)
            is ImageModel.Default -> DefaultImageFormatter().painterFor(imageModel)
        }
    }

    private fun getExt(url: String): String {
        val path = url.split("?", limit = 2)[0]
        return path.split(".").last()
    }

    private fun getImageLoader(url: String) = when (getExt(url).lowercase()) {
        "svg" -> SvgImageLoader()
        "xml" -> XmlImageLoader()
        else -> DefaultImageLoader()
    }
}

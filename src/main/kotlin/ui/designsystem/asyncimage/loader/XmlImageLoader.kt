package ui.designsystem.asyncimage.loader

import androidx.compose.ui.res.loadXmlImageVector
import org.xml.sax.InputSource
import core.Compose
import ui.designsystem.asyncimage.model.ImageModel
import java.io.InputStream

class XmlImageLoader : ImageLoader<ImageModel.Xml>() {

    override fun loadLocal(inputStream: InputStream): ImageModel.Xml {
        val imageVector = loadXmlImageVector(InputSource(inputStream), Compose.density)
        return ImageModel.Xml(imageVector)
    }
}

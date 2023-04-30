package ui.designsystem.asyncimage.model

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ImageModel {

    /** 래스터 이미지
     * JPEG, PNG, WEBP
     */
    data class Default(val imageBitmap: ImageBitmap) : ImageModel()

    data class Svg(val painter: Painter) : ImageModel()

    data class Xml(val imageVector: ImageVector) : ImageModel()
}

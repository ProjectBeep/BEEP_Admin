package ui.designsystem.asyncimage

import ui.designsystem.asyncimage.model.ImageModel

sealed class ImageState {
    data class Success(val imageModel: ImageModel) : ImageState()
    data class Failed(val e: Exception) : ImageState()
    object Loading : ImageState()
}

package ui.designsystem.asyncimage

import androidx.compose.ui.graphics.painter.Painter

sealed class ImageState {
    data class Success(val painter: Painter) : ImageState()
    data class Failed(val e: Exception) : ImageState()
    object Loading : ImageState()
}

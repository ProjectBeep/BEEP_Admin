package ui.designsystem.draganddrop

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.toSize

@Composable
fun DragAndDrop(
    state: DragAndDropState,
    dragOverAllowContent: @Composable () -> Unit = {},
    dragOverPermitContent: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    DisposableEffect(key1 = null) {
        onDispose {
            state.changeDropRect(Rect.Zero)
        }
    }

    Box(
        modifier.onGloballyPositioned {
            state.changeDropRect(Rect(it.positionInWindow(), it.size.toSize()))
        },
    ) {
        content()

        if (state.isDragOver.value) {
            if (state.isDropAllow.value) {
                dragOverAllowContent()
            } else {
                dragOverPermitContent()
            }
        }
    }
}

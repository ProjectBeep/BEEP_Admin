package ui.screen.page

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import model.PageModel
import theme.Dimen

@Composable
fun PageEditorScreen(
    model: PageModel?,
    onChangeDropRect: (Rect) -> Unit = { },
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(Dimen.editWidth),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = if (model != null) "편집하기" else "추가하기")

        Box(
            modifier = Modifier.size(width = Dimen.pageWidth, height = Dimen.pageHeight)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                )
                .onGloballyPositioned {
                    onChangeDropRect(Rect(it.positionInWindow(), it.size.toSize()))
                },
        ) {
        }

        Row {
            Button(onClick = {}) {
            }
        }
    }
}

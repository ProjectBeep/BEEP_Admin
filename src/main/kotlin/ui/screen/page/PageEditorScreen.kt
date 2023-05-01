package ui.screen.page

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import model.PageModel
import theme.Dimen
import ui.designsystem.asyncimage.AsyncImage
import java.io.File

@Composable
fun PageEditorScreen(
    isDragOver: Boolean,
    isDropAllow: Boolean,
    tempFile: File?,
    model: PageModel?,
    onChangeDropRect: (Rect) -> Unit = { },
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(Dimen.editWidth),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
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
            AsyncImage(
                tempFile,
                contentDescription = "임시 파일",
                modifier = Modifier.size(width = Dimen.pageWidth, height = Dimen.pageHeight),
            )

            if (isDragOver) {
                val color = if (isDropAllow) Color.Blue else Color.Red
                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(
                            color = color.copy(
                                alpha = 0.2f,
                            ),
                        )
                        .border(width = 4.dp, color = color),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        color = color,
                        text = if (isDropAllow) {
                            "드랍 하여 추가하세요"
                        } else {
                            "추가 할 수 없는 파일 입니다."
                        },
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }

        Row {
            Button(onClick = {}) {
                Text(text = if (model != null) "수정하기" else "추가하기")
            }
        }
    }
}

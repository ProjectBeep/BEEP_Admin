package ui.screen.page

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import model.PageModel
import theme.Dimen
import ui.designsystem.asyncimage.AsyncImage
import ui.designsystem.draganddrop.DragAndDrop

@Composable
fun PageEditorScreen(
    state: PageEditorState,
    model: PageModel?,
) {
    Column(
        modifier = Modifier.width(Dimen.editWidth)
            .fillMaxHeight(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            Button(onClick = {}) {
                Text(text = if (model != null) "수정하기" else "추가하기")
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(state.scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DragAndDrop(
                modifier = Modifier.size(width = Dimen.pageWidth, height = Dimen.pageHeight)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                    ),
                state = state.dragAndDropState,
                dragOverAllowContent = {
                    Box(
                        modifier = Modifier.fillMaxSize()
                            .background(color = Color.Blue.copy(alpha = 0.2f))
                            .border(width = 4.dp, color = Color.Blue),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            color = Color.Blue,
                            text = "드랍 하여 추가하세요",
                            textAlign = TextAlign.Center,
                        )
                    }
                },
                dragOverPermitContent = {
                    Box(
                        modifier = Modifier.fillMaxSize()
                            .background(color = Color.Red.copy(alpha = 0.2f))
                            .border(width = 4.dp, color = Color.Red),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            color = Color.Red,
                            text = "추가 할 수 없는 파일 입니다.",
                            textAlign = TextAlign.Center,
                        )
                    }
                },
            ) {
                if (state.dragAndDropState.firstDroppedFile.value == null && model != null) {
                    AsyncImage(
                        model.thumbnail,
                        contentDescription = "선택된 이미지",
                        modifier = Modifier.size(width = Dimen.pageWidth, height = Dimen.pageHeight),
                    )
                } else {
                    AsyncImage(
                        state.dragAndDropState.firstDroppedFile.value,
                        contentDescription = "임시 파일",
                        modifier = Modifier.size(width = Dimen.pageWidth, height = Dimen.pageHeight),
                    )
                }
            }

            InputTextField(
                label = "이름",
                value = state.displayName.value,
                onValueChange = {
                    state.displayName.value = it
                },
                modifier = Modifier.padding(top = 16.dp),
            )

            InputTextField(
                label = "경로",
                value = state.dir.value,
                onValueChange = {
                    state.dir.value = it
                },
                modifier = Modifier.padding(top = 16.dp),
            )

            InputTextField(
                label = "피그마 링크",
                value = state.figmaUrl.value,
                onValueChange = {
                    state.figmaUrl.value = it
                },
                modifier = Modifier.padding(top = 16.dp),
            )

            InputTextField(
                label = "제플린 링크",
                value = state.zeplinUrl.value,
                onValueChange = {
                    state.zeplinUrl.value = it
                },
                modifier = Modifier.padding(top = 16.dp),
            )

            InputTextField(
                label = "위키 링크",
                value = state.wikiUrl.value,
                onValueChange = {
                    state.wikiUrl.value = it
                },
                modifier = Modifier.padding(top = 16.dp),
            )
        }
    }
}

@Composable
private fun InputTextField(
    label: String,
    value: String?,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(0.9f),
            label = {
                Text(label)
            },
            value = value ?: "",
            onValueChange = {
                onValueChange(it)
            },
        )
    }
}

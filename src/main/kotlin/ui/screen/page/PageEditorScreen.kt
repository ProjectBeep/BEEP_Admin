package ui.screen.page

import androidx.compose.foundation.ScrollState
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
import androidx.compose.runtime.DisposableEffect
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
    scrollState: ScrollState,
    model: PageModel?,
    isDragOver: Boolean,
    isDropAllow: Boolean,
    tempFile: File?,
    displayNameValue: String,
    dirValue: String,
    figmaUrlValue: String,
    zeplinUrlValue: String,
    wikiUrlValue: String,
    onChangeDropRect: (Rect) -> Unit = { },
    onDisplayNameChange: (String) -> Unit = {},
    onDirChange: (String) -> Unit = {},
    onFigmaUrlChange: (String) -> Unit = {},
    onZeplinUrlChange: (String) -> Unit = {},
    onWikiUrlChange: (String) -> Unit = {},
) {
    DisposableEffect(key1 = null) {
        onDispose {
            onChangeDropRect(Rect.Zero)
        }
    }

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
                .verticalScroll(scrollState),
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
                if (tempFile == null && model != null) {
                    AsyncImage(
                        model.thumbnail,
                        contentDescription = "선택된 이미지",
                        modifier = Modifier.size(width = Dimen.pageWidth, height = Dimen.pageHeight),
                    )
                } else {
                    AsyncImage(
                        tempFile,
                        contentDescription = "임시 파일",
                        modifier = Modifier.size(width = Dimen.pageWidth, height = Dimen.pageHeight),
                    )
                }

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

            InputTextField(
                label = "이름",
                value = displayNameValue,
                onValueChange = onDisplayNameChange,
                modifier = Modifier.padding(top = 16.dp),
            )

            InputTextField(
                label = "경로",
                value = dirValue,
                onValueChange = onDirChange,
                modifier = Modifier.padding(top = 16.dp),
            )

            InputTextField(
                label = "피그마 링크",
                value = figmaUrlValue,
                onValueChange = onFigmaUrlChange,
                modifier = Modifier.padding(top = 16.dp),
            )

            InputTextField(
                label = "제플린 링크",
                value = zeplinUrlValue,
                onValueChange = onZeplinUrlChange,
                modifier = Modifier.padding(top = 16.dp),
            )

            InputTextField(
                label = "위키 링크",
                value = wikiUrlValue,
                onValueChange = onWikiUrlChange,
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

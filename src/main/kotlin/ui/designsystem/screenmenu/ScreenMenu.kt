package ui.designsystem.screenmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.PageModel

@Composable
fun ScreenMenu(
    pageList: List<PageModel>,
    current: PageModel?,
    onPageChange: (PageModel) -> Unit = {},
    screenMenuState: ScreenMenuState = remember { ScreenMenuState() },
) {
    DisposableEffect(key1 = null) {
        onDispose {
            screenMenuState.changeExpanded(false)
        }
    }

    Text(
        modifier = Modifier.width(400.dp)
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .border(width = 1.dp, color = Color.Black)
            .clickable {
                screenMenuState.changeExpanded(true)
            }
            .padding(vertical = 8.dp, horizontal = 16.dp),
        text = current?.displayName ?: "",
    )
    DropdownMenu(
        modifier = Modifier
            .requiredSizeIn(minWidth = 400.dp, maxHeight = 400.dp),
        expanded = screenMenuState.expanded.value,
        onDismissRequest = {
            screenMenuState.changeExpanded(false)
        },
    ) {
        pageList.forEach {
            DropdownMenuItem(
                modifier = if (current == it) {
                    Modifier.background(
                        color = Color.Black.copy(
                            alpha = 0.15f,
                        ),
                    )
                } else {
                    Modifier
                },
                onClick = {
                    onPageChange(it)
                    screenMenuState.changeExpanded(false)
                },
            ) {
                Text(text = it.displayName)
            }
        }
    }
}

package ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun PageScreen() {
    val viewModel = remember { PageViewModel() }
    Column {
        PageToolbar(
            viewModel.showEdit.value,
            onShowEditToggle = {
                viewModel.showEdit.value = it
            },
        )
        Row {
            PageListScreen(modifier = Modifier.weight(1f))
            if (viewModel.showEdit.value) {
                EditPageScreen()
            }
        }
    }
}

@Composable
private fun PageToolbar(
    showAdd: Boolean,
    onShowEditToggle: (Boolean) -> Unit,
) {
    Box(modifier = Modifier.fillMaxWidth().height(48.dp)) {
        Icon(
            modifier = Modifier.align(Alignment.CenterEnd).size(36.dp).clickable {
                onShowEditToggle(!showAdd)
            },
            painter = painterResource(
                if (showAdd) "drawable/icon_hide_edit.svg" else "drawable/icon_show_edit.svg",
            ),
            contentDescription = "화면 추가 축소/확장 아이콘",
        )
    }
}

@Composable
private fun PageListScreen(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.fillMaxHeight().background(Color.Red))
}

@Composable
private fun EditPageScreen() {
    Spacer(modifier = Modifier.fillMaxHeight().width(400.dp).background(Color.Green))
}

package ui.screen.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun PageToolbar(
    showEdit: Boolean,
    onShowEditToggle: (Boolean) -> Unit,
) {
    Box(modifier = Modifier.fillMaxWidth().height(48.dp)) {
        Icon(
            modifier = Modifier.align(Alignment.CenterEnd).size(36.dp).clickable {
                onShowEditToggle(!showEdit)
            },
            painter = painterResource(
                if (showEdit) "drawable/icon_hide_edit.svg" else "drawable/icon_show_edit.svg",
            ),
            contentDescription = "화면 추가 축소/확장 아이콘",
        )
    }
}

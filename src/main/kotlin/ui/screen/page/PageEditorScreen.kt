package ui.screen.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import theme.Dimen

@Composable
fun PageEditorScreen() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(Dimen.editWidth),
    ) {
        Text(text = "편집")
    }
}

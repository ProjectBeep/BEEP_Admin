package ui.screen.color

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.PageModel
import ui.screen.ScreenState

@Composable
fun ColorScreen(
    pageList: List<PageModel>,
    screenState: ScreenState,
) {
    Column(
        Modifier.fillMaxSize()
            .background(Color.Blue),
        Arrangement.spacedBy(5.dp),
    ) {
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                println("클릭 했습니다.")
            },
        ) {
            Text("Hello World")
        }
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                println("초기화")
            },
        ) {
            Text("Reset")
        }
    }
}

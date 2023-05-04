package ui.screen.color

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import model.ColorModel
import model.PageModel
import theme.Dimen
import ui.designsystem.screenmenu.ScreenMenu
import ui.screen.ScreenState

@Composable
fun ColorScreen(
    pageList: List<PageModel>,
    screenState: ScreenState,
    colorScreenState: ColorScreenState,
    onImportClick: () -> Unit,
    onExportClick: () -> Unit,
) {
    Column {
        ColorToolbar(
            pageList = pageList,
            current = screenState.selectedModel.value,
            onPageChange = {
                screenState.selectModel(it)
            },
            onImportClick = onImportClick,
            onExportClick = onExportClick,
        )
        ColorTable(
            modifier = Modifier.weight(1f, true),
            colorList = colorScreenState.colorList.value,
        )
        ColorTableAdd {
            colorScreenState.onColorAdd()
        }
    }
}

@Composable
fun ColorToolbar(
    pageList: List<PageModel>,
    current: PageModel?,
    onPageChange: (PageModel) -> Unit,
    onImportClick: () -> Unit = {},
    onExportClick: () -> Unit = {},
) {
    Row {
        ScreenMenu(pageList, current, onPageChange)
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {},
        ) {
            Text(text = "Import")
        }
        Button(
            onClick = {},
        ) {
            Text(text = "Export")
        }
    }
}

@Composable
fun ColorTable(
    colorList: List<ColorModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        ColorTableHeader()
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            itemsIndexed(colorList) { index, item ->
                ColorTableItem(item)
            }
        }
    }
}

@Composable
private fun ColorTableHeader() {
    Row(
        modifier = Modifier.fillMaxWidth()
            .height(Dimen.colorHeight),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp)
                .width(Dimen.colorWidth),
            text = "색상",
            style = TextStyle(
                fontSize = TextUnit(value = 16f, type = TextUnitType.Sp),
                fontWeight = FontWeight(700),
            ),
        )
        Divider(
            modifier = Modifier.fillMaxHeight().width(1.dp)
                .background(Color.Black),
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp)
                .width(Dimen.colorNameWidth),
            text = "이름",
            style = TextStyle(
                fontSize = TextUnit(value = 16f, type = TextUnitType.Sp),
                fontWeight = FontWeight(700),
            ),
        )
        Divider(
            modifier = Modifier.fillMaxHeight().width(1.dp)
                .background(Color.Black),
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp)
                .width(Dimen.colorCodeWidth),
            text = "코드",
            style = TextStyle(
                fontSize = TextUnit(value = 16f, type = TextUnitType.Sp),
                fontWeight = FontWeight(700),
            ),
        )
    }
    Divider(
        modifier = Modifier.fillMaxWidth().height(1.dp)
            .background(Color.Black),
    )
}

@Composable
private fun ColorTableItem(item: ColorModel) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .height(Dimen.colorHeight),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 16.dp)
                .width(Dimen.colorWidth),
        ) {
            Spacer(
                modifier = Modifier.size(24.dp)
                    .background(Color(item.color))
                    .align(Alignment.Center),
            )
        }
        Divider(
            modifier = Modifier.fillMaxHeight().width(1.dp)
                .background(Color.Black),
        )
        BasicTextField(
            modifier = Modifier.padding(horizontal = 16.dp)
                .width(Dimen.colorNameWidth),
            value = item.name,
            onValueChange = {
            },
        )
        Divider(
            modifier = Modifier.fillMaxHeight().width(1.dp)
                .background(Color.Black),
        )
        BasicTextField(
            modifier = Modifier.padding(horizontal = 16.dp)
                .width(Dimen.colorCodeWidth),
            value = item.colorCode,
            onValueChange = {
            },
        )
    }
    Divider(
        modifier = Modifier.fillMaxWidth().height(1.dp)
            .background(Color.Black),
    )
}

@Composable
private fun ColorTableAdd(
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(48.dp)
            .padding(4.dp)
            .border(color = Color.Black, width = 1.dp)
            .clickable {
                onClick()
            },
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center).size(36.dp),
            painter = painterResource("drawable/icon_add.svg"),
            contentDescription = "색상 추가",
        )
    }
}

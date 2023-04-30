package ui.screen.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import model.PageModel
import theme.Dimen
import ui.common.state.LazyGridScrollState
import ui.common.state.persistedLazyGridScrollState
import ui.designsystem.asyncimage.AsyncImage
import ui.screen.ScreenState

@Composable
fun PageScreen(
    screenState: ScreenState,
    pageState: PageState,
) {
    val viewModel = remember { PageViewModel() }

    Column {
        PageToolbar(
            pageState.showEdit.value,
            onShowEditToggle = {
                pageState.changeShowEdit(it)
            },
        )
        Row {
            PageListScreen(
                modifier = Modifier.weight(1f),
                list = viewModel.pageList.value,
                state = pageState.lazyGridScrollState,
                onSelectModel = {
                    screenState.selectModel(it)
                },
            )
            if (pageState.showEdit.value) {
                EditPageScreen()
            }
        }
    }
}

@Composable
private fun PageToolbar(
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

@Composable
private fun PageListScreen(
    modifier: Modifier = Modifier,
    state: LazyGridScrollState,
    list: List<PageModel>,
    onSelectModel: (PageModel) -> Unit,
) {
    LazyVerticalGrid(
        state = persistedLazyGridScrollState(state),
        modifier = modifier,
        columns = GridCells.Adaptive(Dimen.pageWidth),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(list.size) { index ->
            PageItem(list[index], onSelectModel)
        }
    }
}

@Composable
private fun PageItem(
    model: PageModel,
    onSelectModel: (PageModel) -> Unit,
) {
    Box {
        AsyncImage(
            url = model.thumbnail,
            contentDescription = model.displayName,
            modifier = Modifier.size(width = Dimen.pageWidth, height = Dimen.pageHeight)
                .align(Alignment.Center)
                .clickable {
                    onSelectModel(model)
                },
            contentScale = ContentScale.Fit,
        )
    }
}

@Composable
private fun EditPageScreen() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(Dimen.editWidth),
    ) {
        Text(text = "편집")
    }
}

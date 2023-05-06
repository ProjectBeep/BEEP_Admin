package ui.screen.page

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import model.PageModel
import theme.Dimen
import ui.common.state.persistedLazyGridScrollState
import ui.designsystem.asyncimage.AsyncImage

@Composable
fun PageListScreen(
    modifier: Modifier = Modifier,
    state: PageListState,
    selectedModel: PageModel?,
    onSelectModel: (PageModel) -> Unit,
    onSchemeClick: (PageScheme) -> Unit,
    onLinkClick: (String) -> Unit,
) {
    LazyVerticalGrid(
        state = persistedLazyGridScrollState(state.lazyGridScrollState),
        modifier = modifier,
        columns = GridCells.Adaptive(Dimen.pageWidth),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(state.pageList.value) { item ->
            PageItem(
                selected = selectedModel?.dir == item.dir,
                model = item,
                onSelectModel = onSelectModel,
                onSchemeClick = onSchemeClick,
                onLinkClick = onLinkClick,
            )
        }
    }
}

@Composable
private fun PageItem(
    selected: Boolean,
    model: PageModel,
    onSelectModel: (PageModel) -> Unit,
    onSchemeClick: (PageScheme) -> Unit,
    onLinkClick: (String) -> Unit,
) {
    Box {
        val modifier = Modifier.size(width = Dimen.pageWidth, height = Dimen.pageHeight)
            .align(Alignment.Center)
            .clickable {
                onSelectModel(model)
            }

        AsyncImage(
            url = model.thumbnail,
            contentDescription = model.displayName,
            modifier = if (selected) {
                modifier.border(
                    width = 1.dp,
                    color = Color.Blue,
                )
            } else {
                modifier
            },
            contentScale = ContentScale.Fit,
        )

        Text(
            modifier = Modifier.align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .background(color = Color.Black.copy(alpha = 0.3f), shape = RoundedCornerShape(16.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp),

            text = model.displayName,
            style = TextStyle(
                fontWeight = FontWeight(700),
            ),
            color = Color.White,
            textAlign = TextAlign.Center,
        )

        Column(
            modifier = Modifier.align(Alignment.BottomStart)
                .padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            CircleIcon(
                resourcePath = "drawable/icon_image.svg",
                onClick = {
                    onSchemeClick(PageScheme.Image(model))
                },
            )

            CircleIcon(
                modifier = Modifier.padding(top = 12.dp),
                resourcePath = "drawable/icon_color.svg",
                onClick = {
                    onSchemeClick(PageScheme.Color(model))
                },
            )
            CircleIcon(
                modifier = Modifier.padding(top = 12.dp),
                resourcePath = "drawable/icon_font.svg",
                onClick = {
                    onSchemeClick(PageScheme.Font(model))
                },
            )
            CircleIcon(
                modifier = Modifier.padding(top = 12.dp),
                resourcePath = "drawable/icon_text.svg",
                onClick = {
                    onSchemeClick(PageScheme.Text(model))
                },
            )
        }

        Column(
            modifier = Modifier.align(Alignment.BottomEnd)
                .padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            if (model.wikiUrl.isNotEmpty()) {
                CircleIcon(
                    resourcePath = "drawable/icon_wiki_link.svg",
                    onClick = {
                        onLinkClick(model.wikiUrl)
                    },
                )
            }
            if (model.figmaUrl.isNotEmpty()) {
                CircleIcon(
                    modifier = Modifier.padding(top = 12.dp),
                    resourcePath = "drawable/icon_figma_link.svg",
                    onClick = {
                        onLinkClick(model.figmaUrl)
                    },
                )
            }
            if (model.zeplinUrl.isNotEmpty()) {
                CircleIcon(
                    modifier = Modifier.padding(top = 12.dp),
                    resourcePath = "drawable/icon_zeplin_link.svg",
                    onClick = {
                        onLinkClick(model.zeplinUrl)
                    },
                )
            }
        }
    }
}

@Composable
fun CircleIcon(
    resourcePath: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Icon(
        modifier = modifier
            .size(width = 32.dp, height = 32.dp)
            .background(Color.White, CircleShape)
            .border(
                width = 1.dp,
                color = Color.Black.copy(
                    alpha = 0.2f,
                ),
                CircleShape,
            )
            .clip(CircleShape)
            .clickable {
                onClick()
            },
        painter = painterResource(resourcePath),
        contentDescription = resourcePath,
    )
}

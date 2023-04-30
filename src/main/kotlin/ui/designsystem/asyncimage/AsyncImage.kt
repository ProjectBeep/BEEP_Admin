package ui.designsystem.asyncimage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun AsyncImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
) {
    val state by produceState<ImageState>(ImageState.Loading) {
        value = withContext(Dispatchers.IO) {
            AsyncImageCache.getImage(url)
        }
    }

    when (state) {
        is ImageState.Loading -> {
            Spacer(modifier = modifier)
        }

        is ImageState.Failed -> {
            Spacer(modifier = modifier)
        }

        is ImageState.Success -> {
            val painter = (state as ImageState.Success).painter
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = contentScale,
                modifier = modifier,
            )
        }
    }
}

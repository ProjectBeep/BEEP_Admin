package ui.designsystem.asyncimage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

@Composable
fun AsyncImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    failedContent: @Composable () -> Unit = {
        Spacer(modifier = modifier)
    },
    placeholder: @Composable () -> Unit = {
        Spacer(modifier = modifier)
    },
) {
//    val state by produceState<ImageState>(ImageState.Loading) {
//        value = withContext(Dispatchers.IO) {
//            AsyncImageCache.getImage(url)
//        }
//    }

    val scope = rememberCoroutineScope()
    val state = remember { mutableStateOf<ImageState>(ImageState.Loading) }
    scope.launch {
        state.value = withContext(Dispatchers.IO) {
            AsyncImageCache.getImage(url)
        }
    }

    AsyncImage(state.value, contentDescription, modifier, contentScale, failedContent, placeholder)
}

@Composable
fun AsyncImage(
    file: File?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    failedContent: @Composable () -> Unit = {
        Spacer(modifier = modifier)
    },
    placeholder: @Composable () -> Unit = {
        Spacer(modifier = modifier)
    },
) {
    val scope = rememberCoroutineScope()
    val state = remember { mutableStateOf<ImageState>(ImageState.Loading) }
    scope.launch {
        state.value = withContext(Dispatchers.IO) {
            AsyncImageCache.getImage(file)
        }
    }

    AsyncImage(state.value, contentDescription, modifier, contentScale, failedContent, placeholder)
}

@Composable
private fun AsyncImage(
    state: ImageState,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    failedContent: @Composable () -> Unit = {
        Spacer(modifier = modifier)
    },
    placeholder: @Composable () -> Unit = {
        Spacer(modifier = modifier)
    },
) {
    when (state) {
        is ImageState.Loading -> {
            placeholder()
        }

        is ImageState.Failed -> {
            failedContent()
        }

        is ImageState.Success -> {
            Image(
                painter = AsyncImageCache.painterFor(state.imageModel),
                contentDescription = contentDescription,
                contentScale = contentScale,
                modifier = modifier,
            )
        }
    }
}

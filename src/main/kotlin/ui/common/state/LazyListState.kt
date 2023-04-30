package ui.common.state

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
fun persistedLazyGridScrollState(state: LazyGridScrollState): LazyGridState {
    val scrollState = rememberLazyGridState(
        state.firstVisibleItemIndex,
        state.firstVisibleItemScrollOffset,
    )

    DisposableEffect(key1 = null) {
        onDispose {
            state.firstVisibleItemIndex = scrollState.firstVisibleItemIndex
            state.firstVisibleItemScrollOffset = scrollState.firstVisibleItemScrollOffset
        }
    }
    return scrollState
}

class LazyGridScrollState {
    var firstVisibleItemIndex: Int = 0
    var firstVisibleItemScrollOffset: Int = 0
}

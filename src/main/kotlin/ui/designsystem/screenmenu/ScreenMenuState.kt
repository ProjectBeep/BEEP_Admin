package ui.designsystem.screenmenu

import androidx.compose.runtime.mutableStateOf

class ScreenMenuState {

    val expanded = mutableStateOf(false)

    fun changeExpanded(value: Boolean) {
        expanded.value = value
    }
}

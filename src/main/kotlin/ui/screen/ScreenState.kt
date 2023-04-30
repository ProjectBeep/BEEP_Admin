package ui.screen

import androidx.compose.runtime.mutableStateOf
import model.PageModel

class ScreenState {

    val selectedModel = mutableStateOf<PageModel?>(null)

    fun selectModel(model: PageModel?) {
        selectedModel.value = model
    }
}

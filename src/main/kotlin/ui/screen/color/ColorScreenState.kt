package ui.screen.color

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.mutableStateOf
import model.ColorModel

class ColorScreenState {

    val colorScrollState = ScrollState(initial = 0)

    val colorList = mutableStateOf(
        listOf(
            ColorModel("balck", "FF000000"),
            ColorModel("white", "FFFFFFFF"),
            ColorModel("gray", "FF8C8C8C"),
            ColorModel("error", "FFE93B14"),
            ColorModel("yellow", "FFFEDD16"),
        ),
    )
}

package model

data class ColorModel(
    val name: String,
    val colorCode: String,
) {
    val color = when (colorCode.length) {
        6 -> "FF$colorCode".toLong(16)
        8 -> colorCode.toLong(16)
        else -> 0
    }

    companion object {
        val Empty = ColorModel("", "")
    }
}

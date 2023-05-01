package model

data class PageModel(
    val id: String,
    val dir: String,
    val displayName: String,
    val thumbnail: String,
    val figmaUrl: String,
    val zeplinUrl: String,
    val wikiUrl: String,
)

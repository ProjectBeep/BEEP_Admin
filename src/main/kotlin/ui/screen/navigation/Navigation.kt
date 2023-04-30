package ui.screen.navigation

enum class Navigation(
    val title: String,
    val resourcePath: String,
) {

    PAGE("화면", "drawable/icon_nav_web_asset.svg"),
    COLOR("색상", "drawable/icon_nav_color.svg"),
    TEXT("텍스트", "drawable/icon_nav_text.svg"),
}

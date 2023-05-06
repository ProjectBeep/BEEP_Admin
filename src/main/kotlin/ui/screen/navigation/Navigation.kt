package ui.screen.navigation

enum class Navigation(
    val title: String,
    val resourcePath: String,
) {

    PAGE("화면", "drawable/icon_nav_web_asset.svg"),
    IMAGE("이미지", "drawable/icon_nav_image.svg"),
    TEXT("텍스트", "drawable/icon_nav_text.svg"),
    COLOR("색상", "drawable/icon_nav_color.svg"),
    FONT("스타일", "drawable/icon_nav_font.svg"),
}

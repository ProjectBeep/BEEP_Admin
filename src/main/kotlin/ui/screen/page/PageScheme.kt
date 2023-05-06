package ui.screen.page

import model.PageModel
import ui.screen.navigation.Navigation

sealed class PageScheme(
    val navigation: Navigation,
    val model: PageModel,
) {
    class Color(model: PageModel) : PageScheme(
        Navigation.COLOR,
        model,
    )

    class Image(model: PageModel) : PageScheme(
        Navigation.IMAGE,
        model,
    )

    class Font(model: PageModel) : PageScheme(
        Navigation.FONT,
        model,
    )

    class Text(model: PageModel) : PageScheme(
        Navigation.TEXT,
        model,
    )
}

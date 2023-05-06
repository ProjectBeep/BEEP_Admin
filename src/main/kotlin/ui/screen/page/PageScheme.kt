package ui.screen.page

import model.PageModel
import ui.screen.navigation.Navigation

sealed class PageScheme(
    val navigation: Navigation,
    val model: PageModel,
) {
    class Image(model: PageModel) : PageScheme(
        Navigation.IMAGE,
        model,
    )

    class Text(model: PageModel) : PageScheme(
        Navigation.TEXT,
        model,
    )
}

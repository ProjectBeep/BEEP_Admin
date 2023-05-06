package ui.common.utils

import java.awt.Desktop
import java.net.URI

fun openBrowse(link: String) {
    val desktop = Desktop.getDesktop()
    desktop?.browse(URI(link))
}

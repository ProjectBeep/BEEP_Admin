package ui.screen.page

import androidx.compose.runtime.mutableStateOf
import model.PageModel

class PageViewModel {
    private val initList = listOf(
        PageModel(
            "home",
            "홈",
            "https://firebasestorage.googleapis.com/v0/b/beep-3fcc2.appspot.com/o/screens%2Fhome.jpg?alt=media&token=90bcd6f9-2046-4afd-94f4-f51366c6def9",
            "123",
            "",
            "",
        ),
        PageModel(
            "list",
            "리스트",
            "https://firebasestorage.googleapis.com/v0/b/beep-3fcc2.appspot.com/o/screens%2Flist.jpg?alt=media&token=631702bd-94cf-4ffc-ae5a-413c3cc5280c",
            "",
            "123",
            "",
        ),
        PageModel(
            "setting",
            "설정",
            "https://firebasestorage.googleapis.com/v0/b/beep-3fcc2.appspot.com/o/screens%2Fsetting.jpg?alt=media&token=ab811ede-f14c-4cdb-980b-dbcf5ca1047c",
            "",
            "",
            "123",
        ),
        PageModel(
            "gallery",
            "갤러리",
            "https://firebasestorage.googleapis.com/v0/b/beep-3fcc2.appspot.com/o/screens%2Fgallery.jpg?alt=media&token=3eecd640-b248-4a76-89cc-60411c37246e",
            "",
            "",
            "",
        ),
        PageModel(
            "add_gifticon",
            "기프티콘 추가",
            "https://firebasestorage.googleapis.com/v0/b/beep-3fcc2.appspot.com/o/screens%2Fadd_gifticon.jpg?alt=media&token=f971e1cc-7482-4e51-98d2-0394f840c117",
            "",
            "",
            "",
        ),
    )

    val pageList = mutableStateOf(
        initList + initList.map {
            it.copy(dir = "${it.dir}1")
        } + initList.map {
            it.copy(dir = "${it.dir}2")
        },
    )
}

package ayds.winchester.songinfo.moredetails.view

data class MoreDetailsUiState(
    val artistName: String = "",
    val info: String = "",
    val pageUrl: String = "",
    val actionsEnabled: Boolean = false,
    val IMAGE_URL: String = "",
    val FULL_ARTICLE_URL: String = "", //TODO cambiar segun el source
    val sourceInfo: String = ""
)
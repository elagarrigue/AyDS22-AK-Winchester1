package ayds.winchester.songinfo.moredetails.view

data class MoreDetailsUiState(
    val artistName: String = "",
    val info: String = "",
    val pageid: String = "",
    val actionsEnabled: Boolean = false,
    val IMAGE_URL: String = "https://upload.wikimedia.org/wikipedia/commons/8/8c/Wikipedia-logo-v2-es.png",
    val FULL_ARTICLE_URL: String = "https://en.wikipedia.org/?curid=",
)
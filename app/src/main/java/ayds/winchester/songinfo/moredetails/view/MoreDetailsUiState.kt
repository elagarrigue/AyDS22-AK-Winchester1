package ayds.winchester.songinfo.moredetails.view

import ayds.winchester.songinfo.moredetails.model.entities.CardSource

data class MoreDetailsUiState(
    val artistName: String = "",
    val info: String = "",
    val pageUrl: String = "",
    val actionsEnabled: Boolean = false,
    val IMAGE_URL: String = "",
    val FULL_ARTICLE_URL: String = "",
    val sourceInfo: CardSource = CardSource.EMPTY
)
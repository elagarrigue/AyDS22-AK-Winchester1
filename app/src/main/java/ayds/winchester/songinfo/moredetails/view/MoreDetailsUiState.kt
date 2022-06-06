package ayds.winchester.songinfo.moredetails.view

import ayds.winchester.songinfo.moredetails.model.entities.CardSource

data class CardUiState(
    val pageUrl : String = "",
    val info : String = "",
    val sourceInfo : CardSource = CardSource.EMPTY,
    val IMAGE_URL : String = "",
    val actionsEnabled : Boolean = false,
)

data class MoreDetailsUiState(
    val cards: List<CardUiState> = emptyList(),
    val artistName: String = "",
    val FULL_ARTICLE_URL: String = "",
    var indexCard: Int = 0,
){
    fun getCard(): CardUiState = cards[indexCard % cards.size]

    fun nextCard(){
        indexCard++
    }
}
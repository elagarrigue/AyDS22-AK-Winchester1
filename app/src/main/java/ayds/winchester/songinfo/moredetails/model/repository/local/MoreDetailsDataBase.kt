package ayds.winchester.songinfo.moredetails.model.repository.local

import ayds.winchester.songinfo.moredetails.model.entities.Card

interface MoreDetailsDataBase {
    fun saveCards(artist: String?, card: Card)
    fun getCardsByName(cardName: String): List<Card>
}
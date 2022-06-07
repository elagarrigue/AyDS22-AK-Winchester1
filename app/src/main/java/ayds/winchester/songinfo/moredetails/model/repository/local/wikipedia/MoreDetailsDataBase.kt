package ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia

import ayds.winchester.songinfo.moredetails.model.entities.Card

interface MoreDetailsDataBase {
    fun saveArtist(artist: String?, card: Card)
    fun getCardsByName(cardName: String): List<Card>
}
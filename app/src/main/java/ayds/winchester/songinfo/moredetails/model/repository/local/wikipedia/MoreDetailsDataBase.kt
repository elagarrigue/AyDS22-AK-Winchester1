package ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia

import ayds.winchester.songinfo.moredetails.model.entities.Card

interface MoreDetailsDataBase {
    fun saveArtist(artist: String?, wikipedia: Card)
    fun getCardsByName(cardName: String): List<Card>?
}
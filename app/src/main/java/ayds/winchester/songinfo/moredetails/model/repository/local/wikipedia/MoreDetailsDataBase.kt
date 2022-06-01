package ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia

import ayds.winchester.songinfo.moredetails.model.entities.Card

interface MoreDetailsDataBase {
    fun saveArtist(artist: String?, wikipedia: Card)
    fun getCardByName(cardName: String): Card?
}
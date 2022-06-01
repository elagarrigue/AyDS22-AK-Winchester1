package ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia

import ayds.winchester.songinfo.moredetails.model.entities.WikipediaCard

interface MoreDetailsDataBase {
    fun saveArtist(artist: String?, wikipedia: WikipediaCard)
    fun getCardByName(cardName: String): WikipediaCard?
}
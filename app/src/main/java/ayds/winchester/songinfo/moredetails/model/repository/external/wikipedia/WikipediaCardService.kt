package ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia

import ayds.winchester.songinfo.moredetails.model.entities.WikipediaCard

interface WikipediaCardService {
    fun getCard(artistName: String?): WikipediaCard?
}
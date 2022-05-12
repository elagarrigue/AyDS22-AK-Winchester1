package ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia

import ayds.winchester.songinfo.moredetails.model.entities.WikipediaArtistInfo

interface WikipediaArtistInfoService {
    fun getArtistInfo(artistName: String?): WikipediaArtistInfo?
}
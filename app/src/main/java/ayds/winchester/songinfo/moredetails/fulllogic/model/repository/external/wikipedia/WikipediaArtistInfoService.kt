package ayds.winchester.songinfo.moredetails.fulllogic.model.repository.external.wikipedia

import ayds.winchester.songinfo.moredetails.fulllogic.model.entities.WikipediaArtistInfo

interface WikipediaArtistInfoService {

    fun getArtistInfo(artistName: String?): WikipediaArtistInfo?
}
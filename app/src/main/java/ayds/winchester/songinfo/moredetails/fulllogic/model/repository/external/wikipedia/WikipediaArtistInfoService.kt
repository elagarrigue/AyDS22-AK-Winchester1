package ayds.winchester.songinfo.moredetails.fulllogic.model.repository.external.wikipedia

import ayds.winchester.songinfo.moredetails.fulllogic.model.entities.ArtistInfo

interface WikipediaArtistInfoService {

    fun getArtistInfo(artistName: String?): ArtistInfo?
}
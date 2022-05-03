package ayds.winchester.songinfo.moredetails.fulllogic.model.repository.local.wikipedia

import ayds.winchester.songinfo.moredetails.fulllogic.model.entities.WikipediaArtistInfo

interface OtherInfoDataBase {

    fun saveArtist(artist: String?, artistInfo: WikipediaArtistInfo)

    fun getArtistInfoByName(artistName: String): WikipediaArtistInfo?
}
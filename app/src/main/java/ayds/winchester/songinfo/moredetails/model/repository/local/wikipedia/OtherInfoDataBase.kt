package ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia

import ayds.winchester.songinfo.moredetails.model.entities.WikipediaArtistInfo

interface OtherInfoDataBase {

    fun saveArtist(artist: String?, artistInfo: WikipediaArtistInfo)

    fun getArtistInfoByName(artistName: String): WikipediaArtistInfo?
}
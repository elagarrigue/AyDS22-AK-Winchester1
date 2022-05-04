package ayds.winchester.songinfo.moredetails.fulllogic.model.repository

import ayds.winchester.songinfo.moredetails.fulllogic.model.entities.ArtistInfo
import ayds.winchester.songinfo.moredetails.fulllogic.model.entities.EmptyArtistInfo
import ayds.winchester.songinfo.moredetails.fulllogic.model.entities.WikipediaArtistInfo
import ayds.winchester.songinfo.moredetails.fulllogic.model.repository.external.wikipedia.WikipediaArtistInfoService
import ayds.winchester.songinfo.moredetails.fulllogic.model.repository.local.wikipedia.OtherInfoDataBase

interface InfoRepository {

    fun getArtistInfoByName(artistName: String): ArtistInfo
}

internal class InfoRepositoryImpl(
    private val wikipediaLocalStorage: OtherInfoDataBase,
    private val wikipediaArtistInfoService: WikipediaArtistInfoService
) : InfoRepository {

    override fun getArtistInfoByName(artistName: String) : ArtistInfo {
        var artistInfo = wikipediaLocalStorage.getArtistInfoByName(artistName)

        when {
            artistInfo != null -> markArtistInfoAsLocal(artistInfo)
            else -> {
                try {
                    artistInfo = wikipediaArtistInfoService.getArtistInfo(artistName)
                    artistInfo?.let {
                        wikipediaLocalStorage.saveArtist(artistName, it)
                    }
                } catch (e: Exception) {
                    artistInfo = null
                }
            }
        }

        return artistInfo ?: EmptyArtistInfo
    }

    private fun markArtistInfoAsLocal(artistInfo: WikipediaArtistInfo) {
        artistInfo.isLocallyStored = true
    }

}
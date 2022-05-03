package ayds.winchester.songinfo.moredetails.fulllogic.model.repository.external.wikipedia.artistinfo

import ayds.winchester.songinfo.moredetails.fulllogic.model.entities.WikipediaArtistInfo
import ayds.winchester.songinfo.moredetails.fulllogic.model.repository.external.wikipedia.WikipediaArtistInfoService
import retrofit2.Response

internal class WikipediaArtistInfoServiceImpl (
    private val wikipediaArtistInfoAPI: WikipediaArtistInfoAPI,
    private val wikipediaToArtistInfoResolver: WikipediaToArtistInfoResolver,
    ) : WikipediaArtistInfoService {

        override fun getArtistInfo(artistName: String?): WikipediaArtistInfo? {
            val callResponse = getArtistInfoFromService(artistName)
            return wikipediaToArtistInfoResolver.getArtistInfoFromExternalData(callResponse.body())
        }

        private fun getArtistInfoFromService(query: String?): Response<String> {
            return wikipediaArtistInfoAPI.getArtistInfo(query)
                .execute()
        }
}
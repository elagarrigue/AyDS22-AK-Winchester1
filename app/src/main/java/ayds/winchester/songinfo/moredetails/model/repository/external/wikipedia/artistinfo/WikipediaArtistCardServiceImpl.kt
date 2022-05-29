package ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.artistinfo

import ayds.winchester.songinfo.moredetails.model.entities.WikipediaCard
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.WikipediaCardService
import retrofit2.Response

internal class WikipediaArtistCardServiceImpl (
    private val wikipediaArtistInfoAPI: WikipediaArtistInfoAPI,
    private val wikipediaToArtistInfoResolver: WikipediaToArtistInfoResolver,
    ) : WikipediaCardService {

        override fun getCard(artistName: String?): WikipediaCard? {
            val callResponse = getArtistInfoFromService(artistName)
            return wikipediaToArtistInfoResolver.getCardFromExternalData(callResponse.body())
        }

        private fun getArtistInfoFromService(query: String?): Response<String> {
            return wikipediaArtistInfoAPI.getArtistInfo(query)
                .execute()
        }
}
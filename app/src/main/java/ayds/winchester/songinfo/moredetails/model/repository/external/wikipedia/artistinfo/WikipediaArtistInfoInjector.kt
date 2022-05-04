package ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.artistinfo

import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.WikipediaArtistInfoService
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object WikipediaArtistInfoInjector {

    private const val WIKIPEDIA_API_BASE_URL = "https://en.wikipedia.org/w/"
    private val wikipediaAPIRetrofit = Retrofit.Builder()
        .baseUrl(WIKIPEDIA_API_BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()
    private val wikipediaArtistInfoAPI = wikipediaAPIRetrofit.create(WikipediaArtistInfoAPI::class.java)
    private val wikipediaToArtistInfoResolver: WikipediaToArtistInfoResolver = JsonToArtistInfoResolver()

    val wikipediaArtistInfoService: WikipediaArtistInfoService = WikipediaArtistInfoServiceImpl(
        wikipediaArtistInfoAPI,
        wikipediaToArtistInfoResolver
    )

}
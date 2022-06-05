package ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys

import ayds.newyork2.newyorkdata.nytimes.NYTimesInjector
import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import ayds.newyork2.newyorkdata.nytimes.NYTimesService

private const val SOURCE = "New York Times"

internal class ProxyNewYorkTimes () : Proxy{
    private val nytService : NYTimesService = NYTimesInjector.nyTimesService

    override fun getCard(artist: String?): Card {
        var card: Card? = null
        val artistInfo = artist?.let { nytService.getArtist(it) }
        artistInfo?.let {
            card = Card(
                it.artistInfo,
                it.artistUrl,
                getSource(),
                it.source_logo_url,
                false
            )
        }
        return card ?: EmptyCard
    }

    override fun getSource(): String {
        return SOURCE
    }
}
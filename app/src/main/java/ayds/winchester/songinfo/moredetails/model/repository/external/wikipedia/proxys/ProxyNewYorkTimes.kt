package ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys

import ayds.newyork2.newyorkdata.nytimes.NYTimesArtistInfo
import ayds.newyork2.newyorkdata.nytimes.NYTimesInjector
import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import ayds.newyork2.newyorkdata.nytimes.NYTimesService

private const val SOURCE = "New York Times"

internal class ProxyNewYorkTimes () : Proxy{
    private val nytService : NYTimesService = NYTimesInjector.nyTimesService

    override fun getCard(artist: String?): Card {
        var card: Card?
        val nytArtistInfo = artist?.let { nytService.getArtist(it) }
        card = nytArtistInfo?.mapToCard()
        return card ?: EmptyCard
    }

    override fun getSource(): String {
        return SOURCE
    }

    private fun NYTimesArtistInfo.mapToCard(): Card {
        return Card(
            this.artistInfo,
            this.artistUrl,
            getSource(),
            this.source_logo_url,
            false
        )
    }
}
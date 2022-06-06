package ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys

import ayds.newyork2.newyorkdata.nytimes.NYTimesArtistInfo
import ayds.newyork2.newyorkdata.nytimes.NYTimesInjector
import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import ayds.newyork2.newyorkdata.nytimes.NYTimesService
import ayds.winchester.songinfo.moredetails.model.entities.CardSource

internal class ProxyNewYorkTimes () : Proxy{
    private val newYorkTimesCardService : NYTimesService = NYTimesInjector.nyTimesService

    override fun getCard(artist: String?): Card {
        var card: Card?
        val nytArtistInfo = artist?.let { newYorkTimesCardService.getArtist(it) }
        card = nytArtistInfo?.mapToCard()
        return card ?: EmptyCard
    }

    private fun NYTimesArtistInfo.mapToCard(): Card {
        return Card(
            this.artistInfo,
            this.artistUrl,
            CardSource.NEW_YORK_TIMES,
            this.source_logo_url,
            false
        )
    }
}
package ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys

import ayds.lisboa1.lastfm.LastFMArtistBiography
import ayds.lisboa1.lastfm.LastFMInjector
import ayds.lisboa1.lastfm.LastFMService
import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard

private const val SOURCE = "Last FM"

internal class ProxyLastFM () : Proxy {
    private val lastFMService : LastFMService = LastFMInjector.lastFMService


    override fun getCard(artist: String?): Card {
        var card : Card?
        val lastFmArtistInfo = artist?.let { lastFMService.getArtistBio(it) }
        card = lastFmArtistInfo?.mapToCard()
        return card ?: EmptyCard
    }

    override fun getSource(): String {
        return SOURCE;
    }

    private fun LastFMArtistBiography.mapToCard(): Card {
        return Card(
            this.biography,
            this.articleUrl,
            getSource(),
            this.logoUrl,
            false
        )
    }
}
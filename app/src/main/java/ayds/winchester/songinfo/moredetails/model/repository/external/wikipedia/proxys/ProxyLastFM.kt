package ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys

import ayds.lisboa1.lastfm.LastFMArtistBiography
import ayds.lisboa1.lastfm.LastFMInjector
import ayds.lisboa1.lastfm.LastFMService
import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.CardSource
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard

internal class ProxyLastFM () : Proxy {
    private val lastFMCardService : LastFMService = LastFMInjector.lastFMService


    override fun getCard(artist: String?): Card {
        var card : Card?
        val lastFmArtistInfo = artist?.let { lastFMCardService.getArtistBio(it) }
        card = lastFmArtistInfo?.mapToCard()
        return card ?: EmptyCard
    }

    private fun LastFMArtistBiography.mapToCard(): Card {
        return Card(
            this.biography,
            this.articleUrl,
            CardSource.LAST_FM,
            this.logoUrl,
            false
        )
    }
}
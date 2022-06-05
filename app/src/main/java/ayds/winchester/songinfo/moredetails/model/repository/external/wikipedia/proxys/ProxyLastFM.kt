package ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys

import ayds.lisboa1.lastfm.LastFMInjector
import ayds.lisboa1.lastfm.LastFMService
import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard

private const val SOURCE = "Last FM"

internal class ProxyLastFM () : Proxy {
    private val lastFMService : LastFMService = LastFMInjector.lastFMService


    override fun getCard(artist: String?): Card {
        var card : Card? = null
        val artistInfo = artist?.let {lastFMService.getArtistBio(artist)}
        artistInfo?.let {
            card = Card(
                it.biography,
                it.articleUrl,
                getSource(),
                it.logoUrl,
                false
            )
        }
        return card ?: EmptyCard
    }

    override fun getSource(): String {
        return SOURCE;
    }
}
package ayds.winchester.songinfo.moredetails.model.repository.external.proxys

import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.CardSource
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import ayds.winchester1.wikipedia.WikipediaArtistInfo
import ayds.winchester1.wikipedia.WikipediaService

internal class ProxyWikipedia (
    private val wikipediaService: WikipediaService
) : Proxy {

    override fun getCard(artist: String?): Card {
        var card: Card? = try {
            val wikipediaArtistInfo = wikipediaService.getArtistInfo(artist)
            wikipediaArtistInfo?.mapToCard()
        } catch(e: Exception) {
            null
        }
        return card ?: EmptyCard
    }

    private fun WikipediaArtistInfo.mapToCard(): Card {
        return Card(
            this.description,
            this.infoURL,
            CardSource.WIKIPEDIA,
            this.sourceLogoURL,
            false
        )
    }
}
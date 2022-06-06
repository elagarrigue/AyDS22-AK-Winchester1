package ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys

import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.CardSource
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import ayds.winchester1.wikipedia.WikipediaArtistInfo
import ayds.winchester1.wikipedia.WikipediaCardService
import ayds.winchester1.wikipedia.WikipediaInjector

internal class ProxyWikipedia () : Proxy {
    private val wikipediaCardService: WikipediaCardService = WikipediaInjector.wikipediaCardService

    override fun getCard(artist: String?): Card {
        var card: Card?
        val wikipediaArtistInfo = wikipediaCardService.getCard(artist)
        card = wikipediaArtistInfo?.mapToCard()
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
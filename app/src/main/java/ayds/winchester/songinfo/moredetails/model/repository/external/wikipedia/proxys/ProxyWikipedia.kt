package ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys

import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import ayds.winchester1.wikipedia.WikipediaCardService
import ayds.winchester1.wikipedia.WikipediaInjector

internal class ProxyWikipedia () : Proxy {
    private val wikipediaCardService: WikipediaCardService = WikipediaInjector.wikipediaCardService


    override fun getCard(artist: String?): Card {
        var card: Card? = null
        val wikipediaCard = wikipediaCardService.getCard(artist)
        wikipediaCard?.let {
            card = Card(
                it.description,
                it.infoURL,
                "wikipedia",
                it.sourceLogoURL,
                false
            )
        }

        return card ?: EmptyCard
    }
}
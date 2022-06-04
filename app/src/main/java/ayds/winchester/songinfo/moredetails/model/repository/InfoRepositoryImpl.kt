package ayds.winchester.songinfo.moredetails.model.repository

import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import ayds.winchester1.wikipedia.WikipediaCardService
import ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia.MoreDetailsDataBase

interface InfoRepository {
    fun getCardByName(cardName: String): Card
}

internal class InfoRepositoryImpl(
    private val wikipediaLocalStorage: MoreDetailsDataBase,
    private val wikipediaCardService: WikipediaCardService
) : InfoRepository {

    override fun getCardByName(cardName: String): Card {
        var card: Card? = null
        when {
            card != null -> markCardAsLocal(card)
            else -> {
                try {
                    val serviceWikipediaCard = wikipediaCardService.getCard(cardName)
                    //TODO el mapeo no se hace aca hay que moverlo
                    serviceWikipediaCard?.let {
                        card = Card(
                            it.description,
                            it.infoURL,
                            "wikipedia",
                            "https://es.wikipedia.org/wiki/Archivo:Wikipedia-logo-es.png",
                            false
                        )
                    }

                   card?.let {
                        wikipediaLocalStorage.saveArtist(cardName, it)
                    }
                } catch (e: Exception) {
                    card = null
                }
            }
        }
        return card ?: EmptyCard
    }

    private fun markCardAsLocal(card: Card) {
        card.isLocallyStored = true
    }
}
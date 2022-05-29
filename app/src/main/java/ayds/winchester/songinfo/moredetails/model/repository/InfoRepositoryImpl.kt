package ayds.winchester.songinfo.moredetails.model.repository

import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import ayds.winchester.songinfo.moredetails.model.entities.WikipediaCard
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.WikipediaCardService
import ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia.MoreDetailsDataBase

interface InfoRepository {
    fun getCardByName(cardName: String): Card
}

internal class InfoRepositoryImpl(
    private val wikipediaLocalStorage: MoreDetailsDataBase,
    private val wikipediaCardService: WikipediaCardService
) : InfoRepository {

    override fun getCardByName(cardName: String): Card {
        var card = wikipediaLocalStorage.getCardByName(cardName)
        when {
            card != null -> markCardAsLocal(card)
            else -> {
                try {
                    card = wikipediaCardService.getCard(cardName)
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

    private fun markCardAsLocal(card: WikipediaCard) {
        card.isLocallyStored = true
    }
}
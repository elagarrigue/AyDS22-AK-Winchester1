package ayds.winchester.songinfo.moredetails.model.repository

import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.Broker
import ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia.MoreDetailsDataBase

interface CardsRepository {
    fun getCardsByTerm(term: String): List<Card>
}

internal class CardsRepositoryImpl(
    private val cardLocalStorage: MoreDetailsDataBase,
    private val broker: Broker
) : CardsRepository {

    override fun getCardsByTerm(term: String): List<Card> {

        var cards: List<Card>? = cardLocalStorage.getCardsByName(term)
        when {
            cards != null -> cards.forEach {
                markCardAsLocal(it)
            }
            else -> {
                try {
                    cards = broker.getCards(term)
                    cards.forEach {
                        cardLocalStorage.saveArtist(term, it)
                    }
                } catch (e: Exception) {
                    cards = null
                }
            }
        }
        return cards ?: emptyList()
    }

    private fun markCardAsLocal(card: Card) {
        card.isLocallyStored = true
    }
}
package ayds.winchester.songinfo.moredetails.model.repository

import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.repository.external.Broker
import ayds.winchester.songinfo.moredetails.model.repository.local.MoreDetailsDataBase

interface CardsRepository {
    fun getCardsByTerm(term: String): List<Card>
}

internal class CardsRepositoryImpl(
    private val cardLocalStorage: MoreDetailsDataBase,
    private val broker: Broker
) : CardsRepository {

    override fun getCardsByTerm(term: String): List<Card> {
        var cards: List<Card> = cardLocalStorage.getCardsByName(term)
        when {
            cards.isNotEmpty() -> cards.forEach {
                markCardAsLocal(it)
            }
            else -> {
                    cards = broker.getCards(term)
                    cards.forEach {
                        cardLocalStorage.saveCards(term, it)
                    }
            }
        }
        return cards
    }

    private fun markCardAsLocal(card: Card) {
        card.isLocallyStored = true
    }
}
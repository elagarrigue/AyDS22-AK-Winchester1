package ayds.winchester.songinfo.moredetails.model.repository

import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.Broker
import ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia.MoreDetailsDataBase

interface InfoRepository {
    fun getCardByName(cardName: String): List<Card>
}

internal class InfoRepositoryImpl(
    private val wikipediaLocalStorage: MoreDetailsDataBase,
    private val broker: Broker
) : InfoRepository {

    override fun getCardByName(cardName: String): List<Card> {

        var cards: List<Card>? = null
        when {
            cards != null -> cards.forEach {
                markCardAsLocal(it)
            }
            else -> {
                try {
                    cards = broker.getCards(cardName)
                    cards.forEach {
                        wikipediaLocalStorage.saveArtist(cardName, it)
                    }
                } catch (e: Exception) {
                    cards = null
                }
            }
        }
        return cards ?: listOf<Card>(EmptyCard)
    }

    private fun markCardAsLocal(card: Card) {
        card.isLocallyStored = true
    }
}
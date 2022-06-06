package ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia

import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys.Proxy

interface Broker {

    fun getCards(artist: String?): List<Card>
}

internal class BrokerImpl(private val proxies: List<Proxy>) : Broker {

    override fun getCards(artist: String?): List<Card> {
        val cardList = proxies.map {
            it.getCard(artist)
        }
        return cardList.filter { it != EmptyCard }
    }
}
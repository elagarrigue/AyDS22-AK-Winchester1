package ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia

import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys.Proxy

interface Broker {

    fun getCard(artist: String?): Card
}

internal class BrokerImpl(private val proxies: List<Proxy>) : Broker {

    private var proxiesIterator: Iterator<Proxy> = proxies.iterator()

    private fun nextProxy(): Proxy {
        if (!proxiesIterator.hasNext())
            proxiesIterator = proxies.iterator()
        return proxiesIterator.next()
    }

    override fun getCard(artist: String?): Card {
        return nextProxy().getCard(artist);
    }
}
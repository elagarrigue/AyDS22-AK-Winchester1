package ayds.winchester.songinfo.moredetails.model.repository.external.proxies

import ayds.winchester.songinfo.moredetails.model.entities.Card

interface Proxy {
    fun getCard(artist: String?): Card
}
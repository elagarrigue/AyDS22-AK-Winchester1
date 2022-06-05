package ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys

import ayds.winchester.songinfo.moredetails.model.entities.Card

interface Proxy {
    fun getCard(artist: String?): Card
    fun getSource() : String
}
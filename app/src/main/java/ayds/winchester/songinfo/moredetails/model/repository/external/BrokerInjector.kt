package ayds.winchester.songinfo.moredetails.model.repository.external

import ayds.winchester.songinfo.moredetails.model.repository.external.proxies.Proxy
import ayds.winchester.songinfo.moredetails.model.repository.external.proxies.ProxyLastFM
import ayds.winchester.songinfo.moredetails.model.repository.external.proxies.ProxyNewYorkTimes
import ayds.winchester.songinfo.moredetails.model.repository.external.proxies.ProxyWikipedia
import ayds.winchester1.wikipedia.WikipediaInjector.wikipediaService
import ayds.lisboa1.lastfm.LastFMInjector.lastFMService
import ayds.newyork2.newyorkdata.nytimes.NYTimesInjector.nyTimesService


object BrokerInjector {
    private val proxies: List<Proxy> = listOf(
        ProxyWikipedia(wikipediaService),
        ProxyLastFM(lastFMService),
        ProxyNewYorkTimes(nyTimesService)
    )
    val broker: Broker = BrokerImpl(proxies)
}
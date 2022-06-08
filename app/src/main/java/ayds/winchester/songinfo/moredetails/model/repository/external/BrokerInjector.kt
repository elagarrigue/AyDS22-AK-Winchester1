package ayds.winchester.songinfo.moredetails.model.repository.external

import ayds.winchester1.wikipedia.WikipediaInjector.wikipediaService
import ayds.lisboa1.lastfm.LastFMInjector.lastFMService
import ayds.newyork2.newyorkdata.nytimes.NYTimesInjector.nyTimesService
import ayds.winchester.songinfo.moredetails.model.repository.external.proxys.Proxy
import ayds.winchester.songinfo.moredetails.model.repository.external.proxys.ProxyLastFM
import ayds.winchester.songinfo.moredetails.model.repository.external.proxys.ProxyNewYorkTimes
import ayds.winchester.songinfo.moredetails.model.repository.external.proxys.ProxyWikipedia

object BrokerInjector {
    private val proxies: List<Proxy> = listOf(
        ProxyWikipedia(wikipediaService),
        ProxyLastFM(lastFMService),
        ProxyNewYorkTimes(nyTimesService)
    )
    val broker: Broker = BrokerImpl(proxies)
}
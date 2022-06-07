package ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia

import ayds.winchester1.wikipedia.WikipediaInjector.wikipediaService
import ayds.lisboa1.lastfm.LastFMInjector.lastFMService
import ayds.newyork2.newyorkdata.nytimes.NYTimesInjector.nyTimesService
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys.Proxy
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys.ProxyLastFM
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys.ProxyNewYorkTimes
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys.ProxyWikipedia

object BrokerInjector {
    private val proxies: List<Proxy> = listOf(
        ProxyWikipedia(wikipediaService),
        ProxyLastFM(lastFMService),
        ProxyNewYorkTimes(nyTimesService)
    )
    val broker: Broker = BrokerImpl(proxies)
}
package ayds.winchester.songinfo.moredetails.model.repository.external

import ayds.winchester.songinfo.moredetails.model.repository.external.proxys.Proxy
import ayds.winchester.songinfo.moredetails.model.repository.external.proxys.ProxyLastFMInjector.proxyLastFM
import ayds.winchester.songinfo.moredetails.model.repository.external.proxys.ProxyNewYorkTimesInjector.proxyNewYorkTimes
import ayds.winchester.songinfo.moredetails.model.repository.external.proxys.ProxyWikipediaInjector.proxyWikipedia

object BrokerInjector {
    private val proxies: List<Proxy> = listOf(
        proxyWikipedia,
        proxyLastFM,
        proxyNewYorkTimes
    )
    val broker: Broker = BrokerImpl(proxies)
}
package ayds.winchester.songinfo.moredetails.model.repository.external.proxys

import ayds.lisboa1.lastfm.LastFMInjector.lastFMService

object ProxyLastFMInjector{
    val proxyLastFM: Proxy = ProxyLastFM(lastFMService)
}
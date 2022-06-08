package ayds.winchester.songinfo.moredetails.model.repository.external.proxys

import ayds.newyork2.newyorkdata.nytimes.NYTimesInjector.nyTimesService

object ProxyNewYorkTimesInjector{
    val proxyNewYorkTimes: Proxy = ProxyNewYorkTimes(nyTimesService)
}
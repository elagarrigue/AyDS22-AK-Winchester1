package ayds.winchester.songinfo.moredetails.model.repository.external.proxys

import ayds.winchester1.wikipedia.WikipediaInjector.wikipediaService

object ProxyWikipediaInjector{
    val proxyWikipedia: Proxy = ProxyWikipedia(wikipediaService)
}
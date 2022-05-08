package ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia

import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.artistinfo.WikipediaArtistInfoInjector

object WikipediaInjector {
    val wikipediaArtistInfoService: WikipediaArtistInfoService = WikipediaArtistInfoInjector.wikipediaArtistInfoService
}
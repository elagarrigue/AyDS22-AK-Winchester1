package ayds.winchester.songinfo.moredetails.model.repository.external.proxys

import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys.ProxyWikipedia
import ayds.winchester1.wikipedia.WikipediaArtistInfo
import ayds.winchester1.wikipedia.WikipediaService
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class ProxyWikipediaTest {

    private val wikipediaService: WikipediaService = mockk(relaxUnitFun = true)
    private val proxyWikipedia: ProxyWikipedia by lazy {
        ProxyWikipedia(wikipediaService)
    }

    @Test
    fun `on found artist name should return artist info card `() {
//        val wikipediaArtistInfo: WikipediaArtistInfo = mockk()
//        val card: Card = mockk()
//
//        every { wikipediaService.getArtistInfo("artistName") } returns wikipediaArtistInfo
//
//        val result = proxyWikipedia.getCard("artistName")
//
//        card
//
//
//
//        Assert.assertEquals(card, wikipediaCard)
    }

}

package ayds.winchester.songinfo.moredetails.model.repository.external.proxys

import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.CardSource
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import ayds.winchester1.wikipedia.ArtistInfo
import ayds.winchester1.wikipedia.WikipediaArtistInfo
import ayds.winchester1.wikipedia.WikipediaService
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test
import java.lang.Exception

class ProxyWikipediaTest {

    private val wikipediaService: WikipediaService = mockk()
    private val proxyWikipedia: Proxy by lazy {
        ProxyWikipedia(wikipediaService)
    }

    @Test
    fun `on found artist name should return artist info card`() {
        val wArtistInfo = WikipediaArtistInfo("description","infoUrl", "sourceLogoUrl")

        every { wikipediaService.getArtistInfo("artistName") } returns wArtistInfo

        val result = proxyWikipedia.getCard("artistName")

        Assert.assertNotEquals(EmptyCard, result)
    }

    @Test
    fun `on not found artist name should return artist info empty card`() {
        every{ wikipediaService.getArtistInfo("artistName") } throws mockk<Exception>()

        val result = proxyWikipedia.getCard("artistName")

        Assert.assertEquals(EmptyCard, result)
    }
}
package ayds.winchester.songinfo.moredetails.model.repository.external.proxys

import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
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
        val infoCard: Card = mockk(relaxed = true)

        every { proxyWikipedia.getCard("artistName") } returns infoCard

        Assert.assertNotEquals(EmptyCard, infoCard)
    }

    @Test
    fun `on not found artist name should return artist info empty card`() {
        val infoCard: Card = mockk(relaxed = true)

        every { proxyWikipedia.getCard("artistName") } returns EmptyCard
        every { proxyWikipedia.getCard("artistName") } throws mockk<Exception>()

        Assert.assertNotEquals(EmptyCard, infoCard)
    }
}
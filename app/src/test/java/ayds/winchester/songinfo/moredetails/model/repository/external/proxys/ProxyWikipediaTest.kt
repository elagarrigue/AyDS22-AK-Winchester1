package ayds.winchester.songinfo.moredetails.model.repository.external.proxys

import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import ayds.winchester1.wikipedia.WikipediaService
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class ProxyWikipediaTest {

    private val wikipediaService: WikipediaService = mockk()
    private val proxyWikipedia: Proxy by lazy {
        ProxyWikipedia(wikipediaService)
    }

    @Test
    fun `on found artist name should return artist info card `() {
        val emptyCard: EmptyCard = mockk(relaxed = true)
        val infoCard: Card = mockk(relaxed = true)
        every { proxyWikipedia.getCard("artistName") } returns infoCard
        Assert.assertNotEquals(emptyCard, infoCard)
    }

    @Test
    fun `on not found artist name should return artist info empty card `() {
        val emptyCard: EmptyCard = mockk(relaxed = true)
        val infoCard: Card = mockk(relaxed = true)
        every { proxyWikipedia.getCard("artistName") } returns emptyCard
        Assert.assertNotEquals(emptyCard, infoCard)
    }

}

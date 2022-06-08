package ayds.winchester.songinfo.moredetails.model.repository.external.proxys

import ayds.newyork2.newyorkdata.nytimes.NYTimesService
import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class ProxyNewYorkTimesTest {

    private val newYorkTimesService: NYTimesService = mockk()
    private val proxyNewYorkTimes: Proxy by lazy {
        ProxyNewYorkTimes(newYorkTimesService)
    }

    @Test
    fun `on found artist name should return artist info card`() {
        val emptyCard: EmptyCard = mockk(relaxed = true)
        val infoCard: Card = mockk(relaxed = true)

        every { proxyNewYorkTimes.getCard("artistName") } returns infoCard

        Assert.assertNotEquals(emptyCard, infoCard)
    }

    @Test
    fun `on not found artist name should return artist info empty card`() {
        val emptyCard: EmptyCard = mockk(relaxed = true)
        val infoCard: Card = mockk(relaxed = true)

        every { proxyNewYorkTimes.getCard("artistName") } returns emptyCard

        Assert.assertNotEquals(emptyCard, infoCard)
    }
}
package ayds.winchester.songinfo.moredetails.model.repository.external.proxys

import ayds.newyork2.newyorkdata.nytimes.NYTimesService
import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test
import java.lang.Exception

class ProxyNewYorkTimesTest {

    private val newYorkTimesService: NYTimesService = mockk()
    private val proxyNewYorkTimes: Proxy by lazy {
        ProxyNewYorkTimes(newYorkTimesService)
    }

    @Test
    fun `on found artist name should return artist info card`() {
        val infoCard: Card = mockk(relaxed = true)

        every { proxyNewYorkTimes.getCard("artistName") } returns infoCard

        Assert.assertNotEquals(EmptyCard, infoCard)
    }

    @Test
    fun `on not found artist name should return artist info empty card`() {
        val infoCard: Card = mockk(relaxed = true)

        every { proxyNewYorkTimes.getCard("artistName") } returns EmptyCard
        every { proxyNewYorkTimes.getCard("artistName") } throws mockk<Exception>()

        Assert.assertNotEquals(EmptyCard, infoCard)
    }

}
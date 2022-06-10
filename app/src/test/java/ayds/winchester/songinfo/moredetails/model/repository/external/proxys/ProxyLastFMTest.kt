package ayds.winchester.songinfo.moredetails.model.repository.external.proxys

import ayds.lisboa1.lastfm.LastFMService
import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test
import java.lang.Exception

class ProxyLastFMTest {

    private val lastFMService: LastFMService = mockk()
    private val proxyLastFM: Proxy by lazy {
        ProxyLastFM(lastFMService)
    }

    @Test
    fun `on found artist name should return artist info card`() {
        val infoCard: Card = mockk(relaxed = true)

        every { proxyLastFM.getCard("artistName") } returns infoCard

        Assert.assertNotEquals(EmptyCard, infoCard)
    }

    @Test
    fun `on not found artist name should return artist info empty card`() {
        val infoCard: Card = mockk(relaxed = true)

        every { proxyLastFM.getCard("artistName") } returns EmptyCard
        every { proxyLastFM.getCard("artistName") } throws mockk<Exception>()

        Assert.assertNotEquals(EmptyCard, infoCard)
    }
}
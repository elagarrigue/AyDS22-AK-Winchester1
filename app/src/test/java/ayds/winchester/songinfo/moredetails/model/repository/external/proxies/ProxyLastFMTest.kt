package ayds.winchester.songinfo.moredetails.model.repository.external.proxies

import ayds.lisboa1.lastfm.LastFMArtistBiography
import ayds.lisboa1.lastfm.LastFMService
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class ProxyLastFMTest {

    private val lastFMService: LastFMService = mockk()
    private val proxyLastFM: Proxy by lazy {
        ProxyLastFM(lastFMService)
    }

    @Test
    fun `on found artist name should return artist info card`() {
        val infoCard : LastFMArtistBiography = mockk(relaxed = true)

        every { lastFMService.getArtistBio("artistName") } returns infoCard

        val result = proxyLastFM.getCard("artistName")

        Assert.assertNotEquals(EmptyCard, result)
    }

    @Test
    fun `on not found artist name should return artist info empty card`() {
        every { lastFMService.getArtistBio("artistName") } throws mockk<Exception>()

        val result = proxyLastFM.getCard("artistName")

        Assert.assertEquals(EmptyCard, result)
    }

    @Test
    fun `on service exception thrown should return an empty card`() {
        every { lastFMService.getArtistBio("artistName") } returns null

        val result = proxyLastFM.getCard("artistName")

        Assert.assertEquals(EmptyCard, result)
    }
}
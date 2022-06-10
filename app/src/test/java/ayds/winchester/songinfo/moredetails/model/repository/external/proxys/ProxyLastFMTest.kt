package ayds.winchester.songinfo.moredetails.model.repository.external.proxys

import ayds.lisboa1.lastfm.LastFMArtistBiography
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
        val infoCard : LastFMArtistBiography = mockk()

        every { lastFMService.getArtistBio("artistName") } returns infoCard

        val result = proxyLastFM.getCard("artistName")

        Assert.assertNotEquals(EmptyCard, infoCard)
    }

    @Test
    fun `on not found artist name should return artist info empty card`() {
        every { lastFMService.getArtistBio("artistName") } throws mockk<Exception>()

        val result = proxyLastFM.getCard("artistName")

        Assert.assertEquals(EmptyCard, result)
    }
}
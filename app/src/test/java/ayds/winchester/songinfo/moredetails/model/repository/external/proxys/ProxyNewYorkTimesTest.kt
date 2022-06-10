package ayds.winchester.songinfo.moredetails.model.repository.external.proxys

import ayds.newyork2.newyorkdata.nytimes.NYTimesArtistInfo
import ayds.newyork2.newyorkdata.nytimes.NYTimesService
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
        val artistInfo: NYTimesArtistInfo = mockk(relaxed = true)

        every { newYorkTimesService.getArtist("artistName") } returns artistInfo

        val result = proxyNewYorkTimes.getCard("artistName")

        Assert.assertNotEquals(EmptyCard, result)
    }

    @Test
    fun `on not found artist name should return artist info empty card`() {
        every { newYorkTimesService.getArtist("artistName") } throws mockk<Exception>()

        val result = proxyNewYorkTimes.getCard("artistName")

        Assert.assertEquals(EmptyCard, result)
    }
}
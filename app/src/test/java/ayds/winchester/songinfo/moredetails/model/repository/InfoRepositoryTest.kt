package ayds.winchester.songinfo.moredetails.model.repository

import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import ayds.winchester.songinfo.moredetails.model.entities.WikipediaCard
import ayds.winchester1.spotify.WikipediaCardService
import ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia.MoreDetailsDataBase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test
import java.lang.Exception

class InfoRepositoryTest {

    private val wikipediaLocalStorage: MoreDetailsDataBase = mockk(relaxUnitFun = true)
    private val wikipediaCardService: ayds.winchester1.spotify.WikipediaCardService = mockk(relaxUnitFun = true)

    private val infoRepository: InfoRepository by lazy {
        InfoRepositoryImpl(wikipediaLocalStorage, wikipediaCardService)
    }

    @Test
    fun `given existing artis info by artistName should return artist info and mark it as local`() {
        val artistInfo = WikipediaCard("description", "infoURL","","", false)
        every { wikipediaLocalStorage.getCardByName("artistName") } returns artistInfo

        val result = infoRepository.getCardByName("artistName")

        Assert.assertEquals(artistInfo, result)
        Assert.assertTrue(artistInfo.isLocallyStored)
    }

    @Test
    fun `given non existing artist info by artistName should get the artist info and store it`() {
        val artistInfo = WikipediaCard("description", "infoURL","","", false)
        every { wikipediaLocalStorage.getCardByName("artistName") } returns null
        every { wikipediaCardService.getCard("artistName") } returns artistInfo

        val result = infoRepository.getCardByName("artistName")

        Assert.assertEquals(artistInfo, result)
        Assert.assertFalse(artistInfo.isLocallyStored)
        verify { wikipediaLocalStorage.saveArtist("artistName", artistInfo) }
    }

    @Test
    fun `given non existing artist info by artistName should return empty artist info`() {
        every { wikipediaLocalStorage.getCardByName("artistName") } returns null
        every { wikipediaCardService.getCard("artistName") } returns null

        val result = infoRepository.getCardByName("artistName")

        Assert.assertEquals(EmptyCard, result)
    }

    @Test
    fun `given service exception should return empty artist info`() {
        every { wikipediaLocalStorage.getCardByName("artistName") } returns null
        every { wikipediaCardService.getCard("artistName") } throws mockk<Exception>()

        val result = infoRepository.getCardByName("artistName")

        Assert.assertEquals(EmptyCard, result)
    }
}
package ayds.winchester.songinfo.moredetails.model.repository

import ayds.winchester.songinfo.moredetails.model.entities.EmptyArtistInfo
import ayds.winchester.songinfo.moredetails.model.entities.WikipediaArtistInfo
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.WikipediaArtistInfoService
import ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia.OtherInfoDataBase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test
import java.lang.Exception

class InfoRepositoryTest {

    private val wikipediaLocalStorage: OtherInfoDataBase = mockk(relaxUnitFun = true)
    private val wikipediaArtistInfoService: WikipediaArtistInfoService = mockk(relaxUnitFun = true)

    private val infoRepository: InfoRepository by lazy {
        InfoRepositoryImpl(wikipediaLocalStorage, wikipediaArtistInfoService)
    }

    @Test
    fun `given existing artis info by artistName should return artist info and mark it as local`() {
        val artistInfo = WikipediaArtistInfo("info", "id", false)
        every { wikipediaLocalStorage.getArtistInfoByName("artistName") } returns artistInfo

        val result = infoRepository.getArtistInfoByName("artistName")

        Assert.assertEquals(artistInfo, result)
        Assert.assertTrue(artistInfo.isLocallyStored)
    }

    @Test
    fun `given non existing artist info by artistName should get the artist info and store it`() {
        val artistInfo = WikipediaArtistInfo("info", "id", false)
        every { wikipediaLocalStorage.getArtistInfoByName("artistName") } returns null
        every { wikipediaArtistInfoService.getArtistInfo("artistName") } returns artistInfo

        val result = infoRepository.getArtistInfoByName("artistName")

        Assert.assertEquals(artistInfo, result)
        Assert.assertFalse(artistInfo.isLocallyStored)
        verify { wikipediaLocalStorage.saveArtist("artistName", artistInfo) }
    }

    @Test
    fun `given non existing artist info by artistName should return empty artist info`() {
        every { wikipediaLocalStorage.getArtistInfoByName("artistName") } returns null
        every { wikipediaArtistInfoService.getArtistInfo("artistName") } returns null

        val result = infoRepository.getArtistInfoByName("artistName")

        Assert.assertEquals(EmptyArtistInfo, result)
    }

    @Test
    fun `given service exception should return empty artist info`() {
        every { wikipediaLocalStorage.getArtistInfoByName("artistName") } returns null
        every { wikipediaArtistInfoService.getArtistInfo("artistName") } throws mockk<Exception>()

        val result = infoRepository.getArtistInfoByName("artistName")

        Assert.assertEquals(EmptyArtistInfo, result)
    }
}
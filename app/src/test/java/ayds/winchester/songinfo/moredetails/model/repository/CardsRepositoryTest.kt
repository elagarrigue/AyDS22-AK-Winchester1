package ayds.winchester.songinfo.moredetails.model.repository

import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import ayds.winchester.songinfo.moredetails.model.repository.external.BrokerImpl
import ayds.winchester.songinfo.moredetails.model.repository.external.proxies.ProxyWikipedia
import ayds.winchester.songinfo.moredetails.model.repository.local.MoreDetailsDataBase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test
import java.lang.Exception

class CardsRepositoryTest {

    private val cardLocalStorage: MoreDetailsDataBase = mockk(relaxUnitFun = true)
    private val broker: BrokerImpl = mockk(relaxed = true)

    private val cardsRepository: CardsRepository by lazy {
        CardsRepositoryImpl(cardLocalStorage, broker)
    }

    @Test
    fun `given existing artist info by artistName should return artist info card collection and mark them as local`() {
        val artistInfo: List<Card> = mockk(relaxed = true)
        every { cardLocalStorage.getCardsByName("artistName") } returns artistInfo

        val result = cardsRepository.getCardsByTerm("artistName")

        Assert.assertEquals(artistInfo, result)
        artistInfo.forEach{Assert.assertTrue(it.isLocallyStored)}
    }

    @Test
    fun `given non existing artist info by artistName should get the artist info cards and store them`() {
        val artistInfo: List<Card> = listOf(EmptyCard)
        every { cardLocalStorage.getCardsByName("artistName") } returns emptyList()
        every { broker.getCards("artistName") } returns artistInfo

        val result = cardsRepository.getCardsByTerm("artistName")

        Assert.assertEquals(artistInfo, result)
        artistInfo.forEach{
            Assert.assertFalse(it.isLocallyStored)
            verify { cardLocalStorage.saveCards("artistName", it) }
        }

    }

    @Test
    fun `given non existing artist info by artistName should return empty collection of artist info cards`() {
        every { cardLocalStorage.getCardsByName("artistName") } returns emptyList()
        every { broker.getCards("artistName") } returns listOf(EmptyCard)

        val result = cardsRepository.getCardsByTerm("artistName")

        Assert.assertEquals(listOf(EmptyCard), result)
    }

    @Test
    fun `given service exception it should return an empty card`() {
        val proxyWikipedia : ProxyWikipedia = mockk()

        every { cardLocalStorage.getCardsByName("artistName") } returns mutableListOf()
        every { proxyWikipedia.getCard("artistName") } throws mockk<Exception>()

        val result = cardsRepository.getCardsByTerm("artistName")

        Assert.assertEquals(emptyList<Card>(), result)
    }
}
package ayds.winchester.songinfo.moredetails.model.repository.external

import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.CardSource
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import ayds.winchester.songinfo.moredetails.model.repository.external.proxies.ProxyLastFM
import ayds.winchester.songinfo.moredetails.model.repository.external.proxies.ProxyNewYorkTimes
import ayds.winchester.songinfo.moredetails.model.repository.external.proxies.ProxyWikipedia
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

class BrokerTest{
    private val proxyWikipedia : ProxyWikipedia = mockk()
    private val proxyNewYorkTimes : ProxyNewYorkTimes = mockk()
    private val proxyLastFM : ProxyLastFM = mockk()

    private val broker: Broker = BrokerImpl(listOf(proxyWikipedia,proxyNewYorkTimes,proxyLastFM))

    private val proxies = listOf(proxyWikipedia, proxyNewYorkTimes, proxyLastFM)

    @Test
    fun `given a non existing artist, should return an empty list`() {
        for (proxy in proxies) {
            every { proxy.getCard("test") } returns EmptyCard
        }

        val result = broker.getCards("test")
        val expected : List<Card?> = emptyList()

        assertEquals(expected,result)
    }

    @Test
    fun `given the name of a real artist, and all sources found results, should return a card list`(){
        val cWikipedia = Card("description", "infoUrl", CardSource.WIKIPEDIA, "sourceLogoUrl", false)
        val cNewYork = Card("description", "infoUrl", CardSource.NEW_YORK_TIMES, "sourceLogoUrl", false)
        val cLastFm = Card("description", "infoUrl", CardSource.LAST_FM, "sourceLogoUrl", false)

        every {proxyWikipedia.getCard("name")} returns cWikipedia
        every {proxyNewYorkTimes.getCard("name")} returns cNewYork
        every {proxyLastFM.getCard("name")} returns cLastFm

        val result = broker.getCards("name")
        val expected = listOf(cWikipedia,cNewYork,cLastFm)

        assertEquals(expected,result)
    }

    @Test
    fun `given the name of a real artist, but not all sources found results, should return list of cards`(){
        var amountOfEmptyCard = Random.nextInt(1, proxies.size - 1)

        for (proxy in proxies) {
            if (amountOfEmptyCard > 0) {
                every { proxy.getCard("name")} returns EmptyCard
                --amountOfEmptyCard
            }
            else {
                every { proxy.getCard("name")} returns mockk<Card>()
            }
        }

        val result = broker.getCards("name")

        val expected = mutableListOf<Card>()
        for (proxy in proxies) {
            expected.add(proxy.getCard("name"))
        }

        Assert.assertTrue(expected.containsAll(result))
        Assert.assertTrue(expected.contains(EmptyCard))
        Assert.assertFalse(result.contains(EmptyCard))
    }
}
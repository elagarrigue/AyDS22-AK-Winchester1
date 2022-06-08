package ayds.winchester.songinfo.moredetails.model.repository.external

import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.CardSource
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import ayds.winchester.songinfo.moredetails.model.repository.external.proxys.ProxyLastFM
import ayds.winchester.songinfo.moredetails.model.repository.external.proxys.ProxyNewYorkTimes
import ayds.winchester.songinfo.moredetails.model.repository.external.proxys.ProxyWikipedia
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class BrokerTest{
    private val proxyWikipedia : ProxyWikipedia = mockk()
    private val proxyNewYorkTimes : ProxyNewYorkTimes = mockk()
    private val proxyLastFM : ProxyLastFM = mockk()

    private val broker: Broker = BrokerImpl(listOf(proxyWikipedia,proxyNewYorkTimes,proxyLastFM))

    @Test
    fun `given a  non existing artist, should return an empty list`() {
        every {proxyWikipedia.getCard("test")} returns EmptyCard
        every {proxyNewYorkTimes.getCard("test")} returns EmptyCard
        every {proxyLastFM.getCard("test")} returns EmptyCard

        val result = broker.getCards("test")
        val expected : List<Card?> = emptyList()

        assertEquals(expected,result)
    }

    @Test
    fun `given the name of a real artist, should return a card list`(){
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
}





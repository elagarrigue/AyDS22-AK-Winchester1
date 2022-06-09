package ayds.winchester.songinfo.moredetails.model.repository.external.proxys

import ayds.newyork2.newyorkdata.nytimes.NYTimesArtistInfo
import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.EmptyCard
import ayds.newyork2.newyorkdata.nytimes.NYTimesService
import ayds.winchester.songinfo.moredetails.model.entities.CardSource

internal class ProxyNewYorkTimes (
    private val newYorkTimesService : NYTimesService
        ) : Proxy {

    override fun getCard(artist: String?): Card {
        var card: Card? = try{
            val nytArtistInfo = artist?.let { newYorkTimesService.getArtist(it) }
            nytArtistInfo?.mapToCard()
        }catch(e:Exception){
            null
        }
        return card ?: EmptyCard
    }

    private fun NYTimesArtistInfo.mapToCard(): Card {
        return Card(
            this.artistInfo,
            this.artistUrl,
            CardSource.NEW_YORK_TIMES,
            this.source_logo_url,
            false
        )
    }
}
package ayds.winchester.songinfo.moredetails.fulllogic.model

import ayds.observer.Observable
import ayds.observer.Subject
import ayds.winchester.songinfo.moredetails.fulllogic.model.entities.ArtistInfo
import ayds.winchester.songinfo.moredetails.fulllogic.model.repository.InfoRepository

interface MoreDetailsModel {

    val artistInfoObservable: Observable<ArtistInfo>

    fun searchArtistInfo(artistName: String)
}

internal class MoreDetailsModelImpl(private val repository: InfoRepository) : MoreDetailsModel {

    override val artistInfoObservable = Subject<ArtistInfo>()

    override fun searchArtistInfo(artistName: String) {
        repository.getArtistInfoByName(artistName).let{
            artistInfoObservable.notify(it)
        }
    }


}

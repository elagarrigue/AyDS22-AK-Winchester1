package ayds.winchester.songinfo.moredetails.model

import ayds.observer.Observable
import ayds.observer.Subject
import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.repository.InfoRepository

interface MoreDetailsModel {
    val cardsObservable: Observable<List<Card>>

    fun searchCard(cardName: String)
}

internal class MoreDetailsModelImpl(private val repository: InfoRepository) : MoreDetailsModel {

    override val cardsObservable = Subject<List<Card>>()

    override fun searchCard(cardName: String) {
        repository.getCardByName(cardName).let{
            cardsObservable.notify(it)
        }
    }

}

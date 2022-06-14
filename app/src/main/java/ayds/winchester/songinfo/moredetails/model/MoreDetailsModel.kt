package ayds.winchester.songinfo.moredetails.model

import ayds.observer.Observable
import ayds.observer.Subject
import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.repository.CardsRepository

interface MoreDetailsModel {
    val cardsObservable: Observable<List<Card>>

    fun searchCards(term: String)
}

internal class MoreDetailsModelImpl(private val repository: CardsRepository) : MoreDetailsModel {

    override val cardsObservable = Subject<List<Card>>()

    override fun searchCards(term: String) {
        repository.getCardsByTerm(term).let{
            cardsObservable.notify(it)
        }
    }

}

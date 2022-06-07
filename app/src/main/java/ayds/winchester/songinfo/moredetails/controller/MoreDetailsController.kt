package ayds.winchester.songinfo.moredetails.controller

import ayds.observer.Observer
import ayds.winchester.songinfo.moredetails.model.MoreDetailsModel
import ayds.winchester.songinfo.moredetails.view.MoreDetailsUiEvent
import ayds.winchester.songinfo.moredetails.view.MoreDetailsView

interface MoreDetailsController {
    fun setMoreDetailsView (moreDetailsView : MoreDetailsView)
}

internal class MoreDetailsControllerImpl(
    private val moreDetailsModel : MoreDetailsModel
) : MoreDetailsController {

    private lateinit var moreDetailsView : MoreDetailsView

    override fun setMoreDetailsView(moreDetailsView: MoreDetailsView) {
        this.moreDetailsView = moreDetailsView
        moreDetailsView.uiEventObservable.subscribe(observer)
    }

    private val observer: Observer<MoreDetailsUiEvent> =
        Observer { value ->
            when (value) {
                MoreDetailsUiEvent.ViewFullArticle -> viewFullArticle()
                MoreDetailsUiEvent.Search -> searchArtist()
                MoreDetailsUiEvent.NavigateToNextCard -> next()
            }
        }

    private fun viewFullArticle() {
        moreDetailsView.openFullArticle()
    }

    private fun searchArtist() {
        Thread {
            moreDetailsModel.searchCards(moreDetailsView.uiState.artistName)
        }.start()
    }

    private fun next() {
        Thread {
            moreDetailsView.uiState.nextCard()
            moreDetailsView.updateUiComponents()
        }.start()
    }
}

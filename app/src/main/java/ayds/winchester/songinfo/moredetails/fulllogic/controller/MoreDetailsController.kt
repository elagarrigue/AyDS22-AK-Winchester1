package ayds.winchester.songinfo.moredetails.fulllogic.controller

import ayds.observer.Observer
import ayds.winchester.songinfo.moredetails.fulllogic.model.MoreDetailsModel
import ayds.winchester.songinfo.moredetails.fulllogic.view.MoreDetailsUiEvent
import ayds.winchester.songinfo.moredetails.fulllogic.view.MoreDetailsView

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
            }
        }

    private fun viewFullArticle() {

    }
}
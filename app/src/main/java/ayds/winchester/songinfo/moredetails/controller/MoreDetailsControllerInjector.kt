package ayds.winchester.songinfo.moredetails.controller

import ayds.winchester.songinfo.moredetails.model.MoreDetailsModelInjector
import ayds.winchester.songinfo.moredetails.view.MoreDetailsView

object MoreDetailsControllerInjector {
    fun onViewStarted(moreDetailsView: MoreDetailsView) {
        MoreDetailsControllerImpl(MoreDetailsModelInjector.getMoreDetailsModel()).apply {
            setMoreDetailsView(moreDetailsView)
        }
    }
}
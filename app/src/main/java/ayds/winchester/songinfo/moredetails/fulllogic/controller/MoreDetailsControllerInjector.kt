package ayds.winchester.songinfo.moredetails.fulllogic.controller

import ayds.winchester.songinfo.moredetails.fulllogic.model.MoreDetailsModelInjector
import ayds.winchester.songinfo.moredetails.fulllogic.view.MoreDetailsView

object MoreDetailsControllerInjector {
    fun onViewStarted(moreDetailsView: MoreDetailsView) {
        MoreDetailsControllerImpl(MoreDetailsModelInjector.getMoreDetailsModel()).apply {
            setMoreDetailsView(moreDetailsView)
        }
    }
}
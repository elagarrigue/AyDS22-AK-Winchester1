package ayds.winchester.songinfo.moredetails.fulllogic.view

import ayds.winchester.songinfo.moredetails.fulllogic.controller.MoreDetailsControllerInjector
import ayds.winchester.songinfo.moredetails.fulllogic.model.MoreDetailsModelInjector

object MoreDetailsViewInjector {
    fun init(moreDetailsView: MoreDetailsView) {
        MoreDetailsModelInjector.initMoreDetailsModel()
        MoreDetailsControllerInjector.onViewStarted(moreDetailsView)
    }
}
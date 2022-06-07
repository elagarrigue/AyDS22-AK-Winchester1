package ayds.winchester.songinfo.moredetails.view

import ayds.winchester.songinfo.moredetails.controller.MoreDetailsControllerInjector
import ayds.winchester.songinfo.moredetails.model.MoreDetailsModelInjector

object MoreDetailsViewInjector {

    val CARD_INFO_HELPER: CardInfoHelper =
        CardInfoHelperImpl()

    fun init(moreDetailsView: MoreDetailsView) {
        MoreDetailsModelInjector.initMoreDetailsModel(moreDetailsView)
        MoreDetailsControllerInjector.onViewStarted(moreDetailsView)
    }
}
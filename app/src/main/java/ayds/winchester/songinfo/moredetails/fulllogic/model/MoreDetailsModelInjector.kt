package ayds.winchester.songinfo.moredetails.fulllogic.model

import ayds.winchester.songinfo.moredetails.fulllogic.view.MoreDetailsView

object MoreDetailsModelInjector {
    private lateinit var moreDetailsModel: MoreDetailsModel

    fun getMoreDetailsModel(): MoreDetailsModel = moreDetailsModel

    fun initMoreDetailsModel() {
        moreDetailsModel = MoreDetailsModelImpl()
    }
}
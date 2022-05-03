package ayds.winchester.songinfo.moredetails.fulllogic.model

import android.content.Context
import ayds.winchester.songinfo.moredetails.fulllogic.model.repository.local.wikipedia.OtherInfoDataBase
import ayds.winchester.songinfo.moredetails.fulllogic.model.repository.local.wikipedia.database.OtherInfoDataBaseImpl
import ayds.winchester.songinfo.moredetails.fulllogic.view.MoreDetailsView

object MoreDetailsModelInjector {
    private lateinit var moreDetailsModel: MoreDetailsModel

    fun getMoreDetailsModel(): MoreDetailsModel = moreDetailsModel

    fun initMoreDetailsModel(moredetailsView: MoreDetailsView) {
        val otherinfoDataBase: OtherInfoDataBase = OtherInfoDataBaseImpl(
            moredetailsView as Context)

        val repository: InfoRepository =
            InfoRepositoryImpl(otherinfoDataBase)

        moreDetailsModel = MoreDetailsModelImpl(repository)
    }
}
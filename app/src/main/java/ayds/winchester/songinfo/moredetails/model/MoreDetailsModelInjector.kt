package ayds.winchester.songinfo.moredetails.model

import android.content.Context
import ayds.winchester.songinfo.moredetails.model.repository.CardsRepository
import ayds.winchester.songinfo.moredetails.model.repository.CardsRepositoryImpl
import ayds.winchester.songinfo.moredetails.model.repository.local.MoreDetailsDataBase
import ayds.winchester.songinfo.moredetails.model.repository.local.database.CursorToCardMapperImpl
import ayds.winchester.songinfo.moredetails.model.repository.local.database.MoreDetailsDataBaseImpl
import ayds.winchester.songinfo.moredetails.view.MoreDetailsView
import ayds.winchester.songinfo.moredetails.model.repository.external.BrokerInjector.broker

object MoreDetailsModelInjector {
    private lateinit var moreDetailsModel: MoreDetailsModel

    fun getMoreDetailsModel(): MoreDetailsModel = moreDetailsModel

    fun initMoreDetailsModel(moreDetailsView: MoreDetailsView) {
        val moreDetailsDataBase: MoreDetailsDataBase = MoreDetailsDataBaseImpl(
            moreDetailsView as Context, CursorToCardMapperImpl()
        )

        val repository: CardsRepository = CardsRepositoryImpl(moreDetailsDataBase, broker)
        moreDetailsModel = MoreDetailsModelImpl(repository)
    }
}
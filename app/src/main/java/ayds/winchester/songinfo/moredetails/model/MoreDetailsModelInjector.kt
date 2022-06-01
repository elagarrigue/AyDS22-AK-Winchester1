package ayds.winchester.songinfo.moredetails.model

import android.content.Context
import ayds.winchester.songinfo.moredetails.model.repository.InfoRepository
import ayds.winchester.songinfo.moredetails.model.repository.InfoRepositoryImpl
import ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia.MoreDetailsDataBase
import ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia.database.CursorToWikipediaCardMapperImpl
import ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia.database.MoreDetailsDataBaseImpl
import ayds.winchester.songinfo.moredetails.view.MoreDetailsView

object MoreDetailsModelInjector {
    private lateinit var moreDetailsModel: MoreDetailsModel

    fun getMoreDetailsModel(): MoreDetailsModel = moreDetailsModel

    fun initMoreDetailsModel(moreDetailsView: MoreDetailsView) {
        val moreDetailsDataBase: MoreDetailsDataBase = MoreDetailsDataBaseImpl(
            moreDetailsView as Context, CursorToWikipediaCardMapperImpl()
        )
        val wikipediaCardService: ayds.winchester1.wikipedia.WikipediaCardService = ayds.winchester1.wikipedia.WikipediaInjector.wikipediaCardService
        val repository: InfoRepository =
            InfoRepositoryImpl(moreDetailsDataBase, wikipediaCardService)
        moreDetailsModel = MoreDetailsModelImpl(repository)
    }
}
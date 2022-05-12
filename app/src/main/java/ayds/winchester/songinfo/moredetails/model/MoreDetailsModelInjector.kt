package ayds.winchester.songinfo.moredetails.model

import android.content.Context
import ayds.winchester.songinfo.moredetails.model.repository.InfoRepository
import ayds.winchester.songinfo.moredetails.model.repository.InfoRepositoryImpl
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.WikipediaArtistInfoService
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.WikipediaInjector
import ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia.OtherInfoDataBase
import ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia.database.CursorToWikipediaArtistInfoMapperImpl
import ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia.database.OtherInfoDataBaseImpl
import ayds.winchester.songinfo.moredetails.view.MoreDetailsView

object MoreDetailsModelInjector {
    private lateinit var moreDetailsModel: MoreDetailsModel

    fun getMoreDetailsModel(): MoreDetailsModel = moreDetailsModel

    fun initMoreDetailsModel(moreDetailsView: MoreDetailsView) {
        val otherInfoDataBase: OtherInfoDataBase = OtherInfoDataBaseImpl(
            moreDetailsView as Context, CursorToWikipediaArtistInfoMapperImpl()
        )
        val wikipediaArtistInfoService: WikipediaArtistInfoService = WikipediaInjector.wikipediaArtistInfoService
        val repository: InfoRepository =
            InfoRepositoryImpl(otherInfoDataBase, wikipediaArtistInfoService)
        moreDetailsModel = MoreDetailsModelImpl(repository)
    }
}
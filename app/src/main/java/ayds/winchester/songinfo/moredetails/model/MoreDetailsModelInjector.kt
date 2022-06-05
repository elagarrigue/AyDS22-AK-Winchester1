package ayds.winchester.songinfo.moredetails.model

import android.content.Context
import ayds.winchester.songinfo.moredetails.model.repository.InfoRepository
import ayds.winchester.songinfo.moredetails.model.repository.InfoRepositoryImpl
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.Broker
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.BrokerImpl
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys.Proxy
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys.ProxyLastFM
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys.ProxyNewYorkTimes
import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys.ProxyWikipedia
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
        val proxies: List<Proxy> = listOf(
            ProxyLastFM(),
            ProxyNewYorkTimes(),
            ProxyWikipedia()
        )
        val broker: Broker = BrokerImpl(proxies)
        val repository: InfoRepository =
            InfoRepositoryImpl(moreDetailsDataBase, broker)
        moreDetailsModel = MoreDetailsModelImpl(repository)
    }
}
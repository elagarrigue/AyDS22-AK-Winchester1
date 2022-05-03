package ayds.winchester.songinfo.moredetails.fulllogic.model.repository

import ayds.winchester.songinfo.moredetails.fulllogic.model.repository.local.wikipedia.OtherInfoDataBase

interface InfoRepository {
    fun getArtistInfo(): String
    //TODO traer los metodos de OtherInfoWindow
}


internal class InfoRepositoryImpl(
    private val dataBase: OtherInfoDataBase
) : InfoRepository {

    override fun getArtistInfo(): String {
        TODO("Not yet implemented")
    }

}
package ayds.winchester.songinfo.moredetails.fulllogic.model

import ayds.winchester.songinfo.moredetails.fulllogic.model.database.OtherInfoDataBase

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
package ayds.winchester.songinfo.moredetails.fulllogic.model.database

interface OtherInfoDataBase {

    fun saveArtist(artist: String?, info: String?)

    fun getInfo(artist: String): String?
}
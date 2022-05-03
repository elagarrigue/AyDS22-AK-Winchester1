package ayds.winchester.songinfo.moredetails.fulllogic.model.repository.local.wikipedia

interface OtherInfoDataBase {

    fun saveArtist(artist: String?, info: String?)

    fun getInfo(artist: String): String?
}
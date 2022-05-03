package ayds.winchester.songinfo.moredetails.fulllogic.model.entities

private const val NO_RESULTS = "No results"

interface ArtistInfo {
    var info: String
    val pageId: String
    var isLocallyStored: Boolean
}

data class WikipediaArtistInfo(
    override var info: String,
    override val pageId: String,
    override var isLocallyStored: Boolean = false
) : ArtistInfo

object EmptyArtistInfo : ArtistInfo {
    override var info: String = NO_RESULTS
    override val pageId: String = ""
    override var isLocallyStored: Boolean = false
}
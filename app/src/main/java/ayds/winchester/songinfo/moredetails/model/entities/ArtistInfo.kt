package ayds.winchester.songinfo.moredetails.model.entities

private const val NO_RESULTS = "No results"

interface ArtistInfo {
    val info: String
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
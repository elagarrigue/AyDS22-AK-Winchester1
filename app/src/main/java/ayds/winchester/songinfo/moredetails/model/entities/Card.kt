package ayds.winchester.songinfo.moredetails.model.entities

private const val NO_RESULTS = "No results"

interface Card {
    val description: String
    val infoURL: String
    val source: String
    val sourceLogoURL: String
    var isLocallyStored: Boolean
}

data class WikipediaCard(
    override val description: String,
    override val infoURL: String,
    override val source: String,
    override val sourceLogoURL: String,
    override var isLocallyStored: Boolean = false
) : Card

object EmptyCard : Card {
    override val description: String = NO_RESULTS
    override val infoURL: String = ""
    override val source: String = ""
    override val sourceLogoURL: String = ""
    override var isLocallyStored: Boolean = false
}
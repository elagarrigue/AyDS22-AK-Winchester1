package ayds.winchester.songinfo.moredetails.model.entities

open class Card (
    open val description: String,
    open val infoURL: String,
    open val source: String,
    open val sourceLogoURL: String,
    open var isLocallyStored: Boolean = false
)

object EmptyCard : Card(
    description = "not found",
    infoURL= "",
    source = "",
    sourceLogoURL = "",
    isLocallyStored = false
)

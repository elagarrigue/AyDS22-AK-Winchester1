package ayds.winchester.songinfo.moredetails.model.entities

import ayds.winchester.songinfo.moredetails.model.repository.external.wikipedia.proxys.CardSource

open class Card (
    open val description: String,
    open val infoURL: String,
    open val source: CardSource,
    open val sourceLogoURL: String,
    open var isLocallyStored: Boolean = false
)

object EmptyCard : Card(
    description = "not found",
    infoURL= "",
    source = CardSource.EMPTY,
    sourceLogoURL = "",
    isLocallyStored = false
)

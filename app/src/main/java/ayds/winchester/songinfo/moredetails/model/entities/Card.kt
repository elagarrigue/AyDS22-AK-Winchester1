package ayds.winchester.songinfo.moredetails.model.entities

import ayds.winchester.songinfo.home.view.HomeUiState.Companion.DEFAULT_IMAGE

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
    sourceLogoURL = DEFAULT_IMAGE,
    isLocallyStored = false
)

enum class CardSource {
    WIKIPEDIA, LAST_FM, NEW_YORK_TIMES, EMPTY
}

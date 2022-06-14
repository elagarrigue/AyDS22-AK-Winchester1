package ayds.winchester.songinfo.moredetails.view

sealed class MoreDetailsUiEvent {
    object ViewFullArticle : MoreDetailsUiEvent()
    object Search : MoreDetailsUiEvent()
    object NavigateToNextCard: MoreDetailsUiEvent()
}

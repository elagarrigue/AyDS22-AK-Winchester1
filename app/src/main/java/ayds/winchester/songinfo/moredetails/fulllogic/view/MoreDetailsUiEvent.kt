package ayds.winchester.songinfo.moredetails.fulllogic.view

sealed class MoreDetailsUiEvent {
    object ViewFullArticle : MoreDetailsUiEvent()
    object Search : MoreDetailsUiEvent()
}

package ayds.winchester.songinfo.moredetails.fulllogic.view

import ayds.observer.Observable

interface MoreDetailsView {
    val uiEventObservable: Observable<MoreDetailsUiEvent>
    val uiState: MoreDetailsUiState
}
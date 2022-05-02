package ayds.winchester.songinfo.moredetails.fulllogic.view

import ayds.observer.Observable
import ayds.observer.Subject
import ayds.winchester.songinfo.home.view.HomeUiEvent

interface MoreDetailsView {
    val uiEventObservable: Observable<MoreDetailsUiEvent>
    val uiState: MoreDetailsUiState
}

internal class MoreDetailsViewImpl : MoreDetailsView {
    private val onActionSubject = Subject<MoreDetailsUiEvent>()

    override val uiEventObservable: Observable<MoreDetailsUiEvent> = onActionSubject
    override var uiState: MoreDetailsUiState = MoreDetailsUiState()
}
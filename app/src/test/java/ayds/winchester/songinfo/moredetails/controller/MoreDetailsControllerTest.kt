package ayds.winchester.songinfo.moredetails.controller

import ayds.observer.Subject
import ayds.winchester.songinfo.moredetails.model.MoreDetailsModel
import ayds.winchester.songinfo.moredetails.view.MoreDetailsUiEvent
import ayds.winchester.songinfo.moredetails.view.MoreDetailsUiState
import ayds.winchester.songinfo.moredetails.view.MoreDetailsView
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MoreDetailsControllerTest {

    private val moreDetailsModel: MoreDetailsModel = mockk(relaxUnitFun = true)

    private val onActionSubject = Subject<MoreDetailsUiEvent>()
    private val moreDetailsView: MoreDetailsView = mockk(relaxUnitFun = true) {
        every { uiEventObservable } returns onActionSubject
    }

    private val moreDetailsController by lazy {
        MoreDetailsControllerImpl(moreDetailsModel)
    }

    @Before
    fun setup() {
        moreDetailsController.setMoreDetailsView(moreDetailsView)
    }

    @Test
    fun `on search event should search artist`() {
        every { moreDetailsView.uiState } returns MoreDetailsUiState(artistName = "artist")
        onActionSubject.notify(MoreDetailsUiEvent.Search)
        verify { moreDetailsModel.searchCard("artist") }
    }

    @Test
    fun `on view full article event should open external link`() {
        every { moreDetailsView.uiState } returns MoreDetailsUiState()
        onActionSubject.notify(MoreDetailsUiEvent.ViewFullArticle)
        verify { moreDetailsView.openFullArticle() }
    }
}
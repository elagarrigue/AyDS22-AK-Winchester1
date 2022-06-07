package ayds.winchester.songinfo.moredetails.model

import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.repository.CardsRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class MoreDetailsModelTest {

    private val repository: CardsRepository = mockk()

    private val moreDetailsModel: MoreDetailsModel by lazy {
        MoreDetailsModelImpl(repository)
    }

    @Test
    fun `on search artist info it should notify the result`() {
        val artistInfo: List<Card> = mockk()
        every { repository.getCardsByTerm("artistName") } returns artistInfo
        val artistInfoTester: (List<Card>) -> Unit = mockk(relaxed = true)
        moreDetailsModel.cardsObservable.subscribe {
            artistInfoTester(it)
        }
        moreDetailsModel.searchCards("artistName")
        verify { artistInfoTester(artistInfo) }
    }
}
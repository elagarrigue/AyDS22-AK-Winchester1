package ayds.winchester.songinfo.moredetails.model

import ayds.winchester.songinfo.moredetails.model.entities.ArtistInfo
import ayds.winchester.songinfo.moredetails.model.repository.InfoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class MoreDetailsModelTest {

    private val repository: InfoRepository = mockk()

    private val moreDetailsModel: MoreDetailsModel by lazy {
        MoreDetailsModelImpl(repository)
    }

    @Test
    fun `on search artist info it should notify the result`() {
        val artistInfo: ArtistInfo = mockk()
        every { repository.getArtistInfoByName("artistName") } returns artistInfo
        val artistInfoTester: (ArtistInfo) -> Unit = mockk(relaxed = true)
        moreDetailsModel.artistInfoObservable.subscribe {
            artistInfoTester(it)
        }
        moreDetailsModel.searchArtistInfo("artistName")
        verify { artistInfoTester(artistInfo) }
    }
}
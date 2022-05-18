package ayds.winchester.songinfo.home.view

import ayds.winchester.songinfo.home.model.entities.DatePrecision
import ayds.winchester.songinfo.home.model.entities.Song
import ayds.winchester.songinfo.home.model.entities.SpotifySong
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class SongDescriptionHelperTest {

    private val dateFormat: SongDateFormat = mockk()
    private val songDescriptionHelper by lazy { SongDescriptionHelperImpl(dateFormat) }

    @Test
    fun `given a local song it should return the description`() {
        val song: Song = SpotifySong(
            "id",
            "Plush",
            "Stone Temple Pilots",
            "Core",
            "1992-01-01",
            DatePrecision.YEAR,
            "url",
            "url",
            true,
        )
        //TODO implementar el every de songDescriptionHelper
        val result = songDescriptionHelper.getSongDescriptionText(song)

        val expected =
            "Song: Plush [*]\n" +
                    "Artist: Stone Temple Pilots\n" +
                    "Album: Core\n" +
                    "Year: 1992"

        Assert.assertEquals(expected, result)
    }

    @Test
    fun `given a non local song it should return the description`() {
        val song: Song = SpotifySong(
            "id",
            "Plush",
            "Stone Temple Pilots",
            "Core",
            "1992-01-01",
            DatePrecision.YEAR,
            "url",
            "url",
            false,
        )
        //TODO implementar el every de songDescriptionHelper
        val result = songDescriptionHelper.getSongDescriptionText(song)

        val expected =
            "Song: Plush \n" +
                    "Artist: Stone Temple Pilots\n" +
                    "Album: Core\n" +
                    "Year: 1992"

        Assert.assertEquals(expected, result)
    }

    @Test
    fun `given a non spotify song it should return the song not found description`() {
        val song: Song = mockk()

        val result = songDescriptionHelper.getSongDescriptionText(song)

        val expected = "Song not found"

        Assert.assertEquals(expected, result)
    }
}
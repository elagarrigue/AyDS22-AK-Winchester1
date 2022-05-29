package ayds.winchester.songinfo.moredetails.view

import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.WikipediaCard
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class ArtistInfoHelperTest {

    private val artistInfoHelper by lazy { ArtistInfoHelperImpl() }

    @Test
    fun `given a local artist it should return the description`() {

        val artistName = "Stone Temple Pilots"

        val artistInfo: Card = WikipediaCard(
            "Stone Temple Pilots (also known by the initials STP) is an American" +
                    " rock band from San Diego, California.",
            "1234",
            "",
            "",
            true
        )

        val result = artistInfoHelper.artistInfoTextToHtml(artistInfo, artistName)

        val expected =
            "<html><div width=400><font face=\"arial\">[*]<b>STONE TEMPLE PILOTS</b>" +
                    " (also known by the initials STP) is an American rock band from " +
                    "San Diego, California.</font></div></html>"

        Assert.assertEquals(expected, result)
    }

    @Test
    fun `given a non local song it should return the description`() {
        val artistName = "Stone Temple Pilots"

        val artistInfo: Card = WikipediaCard(
            "Stone Temple Pilots (also known by the initials STP) is an American" +
                    " rock band from San Diego, California.",
            "1234",
            "",
            "",
            false
        )

        val result = artistInfoHelper.artistInfoTextToHtml(artistInfo, artistName)

        val expected =
            "<html><div width=400><font face=\"arial\"> <b>STONE TEMPLE PILOTS</b>" +
                    " (also known by the initials STP) is an American rock band from " +
                    "San Diego, California.</font></div></html>"

        Assert.assertEquals(expected, result)
    }

    @Test
    fun `given a non spotify song it should return the song not found description`() {
        val artistInfo: Card = mockk()
        val artistName = "Test"

        val result = artistInfoHelper.artistInfoTextToHtml(artistInfo, artistName)

        val expected = "Artist not found"

        Assert.assertEquals(expected, result)
    }
}
package ayds.winchester.songinfo.moredetails.fulllogic.model.repository.local.wikipedia.database

import android.database.Cursor
import ayds.winchester.songinfo.moredetails.fulllogic.model.entities.WikipediaArtistInfo
import java.sql.SQLException

interface CursorToWikipediaArtistInfoMapper {

    fun map(cursor: Cursor): WikipediaArtistInfo?
}

internal class CursorToWikipediaArtistInfoMapperImpl : CursorToWikipediaArtistInfoMapper {

    override fun map(cursor: Cursor): WikipediaArtistInfo? =
        try {
            with(cursor) {
                if (moveToNext()) {
                    WikipediaArtistInfo(
                        info = getString(getColumnIndexOrThrow(INFO_COLUMN)),
                        pageId = getString(getColumnIndexOrThrow(ARTIST_PAGE_ID_COLUMN)),
                    )
                } else {
                    null
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
}
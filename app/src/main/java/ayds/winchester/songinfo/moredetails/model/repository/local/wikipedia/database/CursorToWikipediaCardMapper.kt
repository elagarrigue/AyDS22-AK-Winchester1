package ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia.database

import android.database.Cursor
import ayds.winchester.songinfo.moredetails.model.entities.WikipediaCard
import java.sql.SQLException

interface CursorToWikipediaCardMapper {
    fun map(cursor: Cursor): WikipediaCard?
}

internal class CursorToWikipediaCardMapperImpl : CursorToWikipediaCardMapper {

    override fun map(cursor: Cursor): WikipediaCard? =
        try {
            with(cursor) {
                if (moveToNext()) {
                    WikipediaCard(
                        description = getString(getColumnIndexOrThrow(INFO_COLUMN)),
                        infoURL = getString(getColumnIndexOrThrow(ARTIST_PAGE_ID_COLUMN)),
                        source = "",
                        sourceLogoURL = ""
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
package ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia.database

import android.database.Cursor
import ayds.winchester.songinfo.moredetails.model.entities.Card
import java.sql.SQLException

interface CursorToWikipediaCardMapper {
    fun map(cursor: Cursor): Card?
}

internal class CursorToWikipediaCardMapperImpl : CursorToWikipediaCardMapper {

    override fun map(cursor: Cursor): Card? =
        try {
            with(cursor) {
                if (moveToNext()) {
                    Card(
                        description = getString(getColumnIndexOrThrow(INFO_COLUMN)),
                        infoURL = getString(getColumnIndexOrThrow(ARTIST_PAGE_ID_COLUMN)),
                        source = getString(getColumnIndexOrThrow(SOURCE_COLUMN)),
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
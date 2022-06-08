package ayds.winchester.songinfo.moredetails.model.repository.local.database

import android.database.Cursor
import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.entities.CardSource
import java.sql.SQLException

interface CursorToCardMapper {
    fun map(cursor: Cursor): List<Card>
}

internal class CursorToCardMapperImpl : CursorToCardMapper {

    override fun map(cursor: Cursor): List<Card> {
        val resultList: MutableList<Card> = mutableListOf()
        try {
            with(cursor) {
                while (moveToNext()) {
                    val storedCardSourceOrdinal = cursor.getInt(
                        getColumnIndexOrThrow(
                            SOURCE_COLUMN
                        )
                    )
                    resultList.add(
                        Card(
                            description = getString(getColumnIndexOrThrow(INFO_COLUMN)),
                            infoURL = getString(getColumnIndexOrThrow(ARTIST_PAGE_ID_COLUMN)),
                            source = CardSource.values()[storedCardSourceOrdinal],
                            sourceLogoURL = getString(getColumnIndexOrThrow(SOURCE_LOGO_URL_COLUMN))
                        )
                    )
                }
            }
        } catch (e: SQLException) {
            return emptyList()
        }
        return resultList
    }
}
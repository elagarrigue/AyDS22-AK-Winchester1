package ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia.database

import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import ayds.winchester.songinfo.moredetails.model.entities.Card
import ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia.MoreDetailsDataBase

private const val DATABASE_NAME = "dictionary.db"
private const val DATABASE_VERSION = 1

internal class MoreDetailsDataBaseImpl(
    context: Context,
    private val cursorToCard: CursorToCardMapper,
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION),
    MoreDetailsDataBase {

    private val projection = arrayOf(
        ID_COLUMN,
        ARTIST_COLUMN,
        INFO_COLUMN,
        SOURCE_COLUMN,
        SOURCE_LOGO_URL_COLUMN,
        ARTIST_PAGE_ID_COLUMN
    )

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createArtistTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    override fun saveArtist(artistName: String?, card: Card) {
            val values = createContentValues(artistName, card)
            writableDatabase?.insert(ARTISTS_TABLE, null, values)
    }

    private fun createContentValues(artistName: String?, artistInfo: Card) :ContentValues{
        val values = ContentValues().apply {
            put(ARTIST_COLUMN, artistName)
            put(INFO_COLUMN, artistInfo.description)
            put(SOURCE_COLUMN, artistInfo.source.ordinal)
            put(SOURCE_LOGO_URL_COLUMN, artistInfo.sourceLogoURL)
            put(ARTIST_PAGE_ID_COLUMN, artistInfo.infoURL)
        }
        return values
    }

    override fun getCardsByName(cardName: String): List<Card>? {
            val cursor = createCursor(cardName)
            return cursorToCard.map(cursor)
        }

    private fun createCursor(artist: String): Cursor {
        return readableDatabase.query(
            ARTISTS_TABLE,
            projection,
            "$ARTIST_COLUMN= ?",
            arrayOf(artist),
            null,
            null,
            "$ARTIST_COLUMN DESC"
        )
    }
}
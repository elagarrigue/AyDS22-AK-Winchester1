package ayds.winchester.songinfo.moredetails.fulllogic.model.repository.local.wikipedia.database

import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import ayds.winchester.songinfo.moredetails.fulllogic.model.entities.WikipediaArtistInfo
import ayds.winchester.songinfo.moredetails.fulllogic.model.repository.local.wikipedia.OtherInfoDataBase

private const val DATABASE_NAME = "dictionary.db"
private const val DATABASE_VERSION = 1

class OtherInfoDataBaseImpl(
    context: Context,
    private val cursorToWikipediaArtistInfo: CursorToWikipediaArtistInfoMapper,
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION),
    OtherInfoDataBase {

    private val projection = arrayOf(
        ID_COLUMN,
        ARTIST_COLUMN,
        INFO_COLUMN,
        SOURCE_COLUMN,
        ARTIST_PAGE_ID_COLUMN
    )
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createArtistTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    override fun saveArtist(artistName: String?, artistInfo: WikipediaArtistInfo) {
            val values = createContentValues(artistName,artistInfo)
            writableDatabase?.insert(ARTISTS_TABLE, null, values)
        }

    private fun createContentValues(artistName: String?, artistInfo: WikipediaArtistInfo) :ContentValues{
        val values = ContentValues().apply {
            put(ARTIST_COLUMN, artistName)
            put(INFO_COLUMN, artistInfo.info)
            put(SOURCE_COLUMN, 1)
            put(ARTIST_PAGE_ID_COLUMN, artistInfo.pageId)
        }
        return values
    }

    override fun getArtistInfoByName(artistName: String): WikipediaArtistInfo? {
            val cursor = createCursor(artistName)
            return cursorToWikipediaArtistInfo.map(cursor)
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
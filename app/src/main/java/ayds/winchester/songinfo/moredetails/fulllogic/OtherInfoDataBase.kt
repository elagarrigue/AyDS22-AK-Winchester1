package ayds.winchester.songinfo.moredetails.fulllogic

import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import java.util.ArrayList

private const val DATABASE_NAME = "dictionary.db"
private const val DATABASE_VERSION = 1

class OtherInfoDataBase(context: Context?)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val projection = arrayOf(
        ID_COLUMN,
        ARTIST_COLUMN,
        INFO_COLUMN,
        SOURCE_COLUMN
    )
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createArtistTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    fun saveArtist(artist: String?, info: String?) {
            val values = createContentValues(artist,info)
            writableDatabase?.insert(ARTISTS_TABLE, null, values)
        }

    private fun createContentValues(artist: String?, info: String?) :ContentValues{
        val values = ContentValues().apply {
            put(ARTIST_COLUMN, artist)
            put(INFO_COLUMN, info)
            put(SOURCE_COLUMN, 1)
        }
        return values
    }

    fun getInfo(artist: String): String? {
            val cursor = createCursor(artist)
            val items = addInfoToList(cursor)
            return items.firstOrNull()
        }

    private fun createCursor(artist: String):Cursor{
        val cursor = readableDatabase.query(
            ARTISTS_TABLE,
            projection,
            "$ARTIST_COLUMN= ?",
            arrayOf(artist),
            null,
            null,
            "$ARTIST_COLUMN DESC"
        )

        return cursor
    }

    private fun addInfoToList(cursor: Cursor): MutableList<String> {
        val items: MutableList<String> = ArrayList()
        while (cursor.moveToNext()) {
            val info = cursor.getString(
                cursor.getColumnIndexOrThrow("$INFO_COLUMN")
            )
            items.add(info)
        }
        cursor.close()
        return items
    }
}
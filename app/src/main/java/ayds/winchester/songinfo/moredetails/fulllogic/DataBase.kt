package ayds.winchester.songinfo.moredetails.fulllogic

import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context
import java.util.ArrayList

private const val DATABASE_NAME = "dictionary.db"
private const val DATABASE_VERSION = 1

class DataBase(context: Context?
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "create table artists (id INTEGER PRIMARY KEY AUTOINCREMENT, artist string, info string, source integer)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    fun saveArtist(artist: String?, info: String?) {
            val values = ContentValues()
            values.put("artist", artist)
            values.put("info", info)
            values.put("source", 1)
            writableDatabase?.insert("artists", null, values)
        }

    fun getInfo(artist: String): String? {
            val projection = arrayOf(
                "id",
                "artist",
                "info"
            )
            val selection = "artist  = ?"
            val selectionArgs = arrayOf(artist)
            val sortOrder = "artist DESC"
            val cursor = readableDatabase.query(
                "artists",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
            )
            val items: MutableList<String> = ArrayList()
            while (cursor.moveToNext()) {
                val info = cursor.getString(
                    cursor.getColumnIndexOrThrow("info")
                )
                items.add(info)
            }
            cursor.close()
            return if (items.isEmpty()) null else items[0]
        }
    }
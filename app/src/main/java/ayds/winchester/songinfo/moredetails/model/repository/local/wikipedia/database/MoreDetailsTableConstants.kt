package ayds.winchester.songinfo.moredetails.model.repository.local.wikipedia.database

const val ARTISTS_TABLE = "artists"
const val ID_COLUMN = "id"
const val ARTIST_COLUMN = "artist"
const val INFO_COLUMN = "info"
const val SOURCE_COLUMN = "source"
const val ARTIST_PAGE_ID_COLUMN = "pageid"

const val createArtistTableQuery: String =
    "create table $ARTISTS_TABLE (" +
            "$ID_COLUMN integer PRIMARY KEY, " +
            "$ARTIST_COLUMN string, " +
            "$INFO_COLUMN string, " +
            "$SOURCE_COLUMN integer, " +
            "$ARTIST_PAGE_ID_COLUMN string )"
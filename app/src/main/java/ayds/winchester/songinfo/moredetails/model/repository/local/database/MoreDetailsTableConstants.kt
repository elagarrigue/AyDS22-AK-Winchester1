package ayds.winchester.songinfo.moredetails.model.repository.local.database

const val CARDS_TABLE = "cards"
const val ID_COLUMN = "id"
const val ARTIST_COLUMN = "artist"
const val INFO_COLUMN = "info"
const val SOURCE_COLUMN = "source"
const val SOURCE_LOGO_URL_COLUMN = "sourcelogourl"
const val ARTIST_PAGE_ID_COLUMN = "pageid"

const val createArtistTableQuery: String =
    "create table $CARDS_TABLE (" +
            "$ID_COLUMN integer PRIMARY KEY, " +
            "$ARTIST_COLUMN string, " +
            "$INFO_COLUMN string, " +
            "$SOURCE_COLUMN integer, " +
            "$SOURCE_LOGO_URL_COLUMN string, " +
            "$ARTIST_PAGE_ID_COLUMN string )"
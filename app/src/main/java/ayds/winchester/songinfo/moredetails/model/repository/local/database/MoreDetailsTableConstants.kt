package ayds.winchester.songinfo.moredetails.model.repository.local.database

const val CARDS_TABLE = "cards"
const val ID_COLUMN = "id"
const val ARTIST_COLUMN = "artist"
const val DESCRIPTION_COLUMN = "description"
const val SOURCE_COLUMN = "source"
const val SOURCE_LOGO_URL_COLUMN = "sourcelogourl"
const val INFO_URL_COLUMN = "infourl"

const val createArtistTableQuery: String =
    "create table $CARDS_TABLE (" +
            "$ID_COLUMN integer PRIMARY KEY, " +
            "$ARTIST_COLUMN string, " +
            "$DESCRIPTION_COLUMN string, " +
            "$SOURCE_COLUMN integer, " +
            "$SOURCE_LOGO_URL_COLUMN string, " +
            "$INFO_URL_COLUMN string )"
package ayds.winchester.songinfo.moredetails.view

import java.lang.StringBuilder

const val OPEN_HTML_TAG = "<html><div width=400><font face=\"arial\">"
const val CLOSE_HTML_TAG = "</font></div></html>"
const val OPEN_B_TAG = "<b>"
const val CLOSE_B_TAG = "</b>"
const val BR_TAG = "<br>"
const val I_TAG = "(?i)"
const val OLD_LINE_BREAK = "\n"
const val SINGLE_QUOTE = "'"
const val SPACE = " "

interface ArtistInfoHelper {
    fun artistInfoTextToHtml(info: String, artistName: String): String
}

internal class ArtistInfoHelperImpl : ArtistInfoHelper {

    private fun addBoldToInfo(info: String, artistName: String): String {
        return info
            .replace(SINGLE_QUOTE, SPACE)
            .replace(OLD_LINE_BREAK, BR_TAG)
            .replace(
                I_TAG + artistName.toRegex(),
                OPEN_B_TAG + artistName.uppercase() + CLOSE_B_TAG
            )
    }

    private fun buildHtml(infoHtml: String): String {
        val builder = StringBuilder()
        builder.append(OPEN_HTML_TAG)
        builder.append(infoHtml)
        builder.append(CLOSE_HTML_TAG)
        return builder.toString()
    }

    override fun artistInfoTextToHtml(info: String, artistName: String): String {
        val textWithBold = addBoldToInfo(info, artistName)
        return buildHtml(textWithBold)
    }
}
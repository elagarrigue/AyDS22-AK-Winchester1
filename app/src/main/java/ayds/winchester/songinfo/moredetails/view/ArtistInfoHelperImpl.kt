package ayds.winchester.songinfo.moredetails.view

import java.lang.StringBuilder

interface ArtistInfoHelper {
    fun artistInfoTextToHtml(info: String, artistName: String?): String
}

internal class ArtistInfoHelperImpl() : ArtistInfoHelper {

    override fun artistInfoTextToHtml(info: String, artistName: String?): String {
        val builder = StringBuilder()
        builder.append("<html><div width=400>")
        builder.append("<font face=\"arial\">")
        val textWithBold = info
            .replace("'", " ")
            .replace("\n", "<br>")
            .replace("(?i)" + artistName!!.toRegex(), "<b>" + artistName.uppercase() + "</b>")
        builder.append(textWithBold)
        builder.append("</font></div></html>")
        return builder.toString()
    }
}
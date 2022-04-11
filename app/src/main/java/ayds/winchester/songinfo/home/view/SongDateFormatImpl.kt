package ayds.winchester.songinfo.home.view

import ayds.winchester.songinfo.home.model.entities.Song

interface SongDateFormat {
    fun getFormattedDate(song : Song) : String
}

internal class SongDateFormatImpl : SongDateFormat {
    override fun getFormattedDate(song : Song) : String {
        val date = song.releaseDate
        val releaseDatePrecision = song.releaseDatePrecision
        return formatterDate(date, releaseDatePrecision)
    }

    private fun formatterDate(date : String, releaseDatePrecision : String) : String {
        var dateFormatted = ""
        when (releaseDatePrecision) {
            "day" -> dateFormatted = dayFormat(date)
            "month" -> dateFormatted = monthFormat(date)
            "year" -> dateFormatted = yearFormat(date)
        }
        return dateFormatted
    }

    private fun leapYear(year : Int) : Boolean {
        var isLeapYear = false
        if ((year % 4 == 0) && (year % 100 != 0 || year % 400 == 0))
            isLeapYear = true
        return isLeapYear
    }

    private fun dayFormat(date : String) : String {
        val year = date.substringBefore("-")
        val day = date.substringAfterLast("-")
        val month = date.subSequence(
            date.indexOf("-") + 1,
            date.lastIndexOf("-")
        )
        return "$day/$month/$year"

    }

    private fun monthFormat(date : String) : String {
        val year = date.substringBefore("-")
        val month = date.substringAfter("-")
        return getMonth(month)+", "+year

    }
    private fun yearFormat(date : String) : String {
        val leapYear = if (leapYear(date.toInt())) " (leap year)" else " (not a leap year)"
        return date+leapYear
    }

    private fun getMonth (month : String) : String {
        val currentMonth = when (month) {
            "01" -> "January"
            "02" -> "February"
            "03" -> "March"
            "04" -> "April"
            "05" -> "May"
            "06" -> "June"
            "07" -> "July"
            "08" -> "August"
            "09" -> "September"
            "10" -> "October"
            "11" -> "November"
            "12" -> "December"
            else -> ""
        }
        return currentMonth
    }
}
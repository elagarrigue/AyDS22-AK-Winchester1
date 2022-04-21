package ayds.winchester.songinfo.home.model.repository.external.spotify.tracks

import ayds.winchester.songinfo.home.model.entities.DatePrecision

interface DatePrecisionHelper {
    fun getDatePrecision(datePrecision : String) : DatePrecision
}

class DatePrecisionHelperImpl:  DatePrecisionHelper{

    override fun getDatePrecision(datePrecision:String) = when (datePrecision) {
        "DAY" -> DatePrecision.DAY
        "MONTH" -> DatePrecision.MONTH
        "YEAR" -> DatePrecision.YEAR
        else -> DatePrecision.DATE
    }
}
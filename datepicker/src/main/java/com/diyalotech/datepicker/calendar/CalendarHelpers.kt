package com.diyalotech.datepicker.calendar

import com.diyalotech.datepicker.date.Constants
import com.diyalotech.datepicker.date.NepDate

internal val YEAR_RANGE = Constants.bsLBoundY..Constants.bsUBoundY

internal fun getDates(date: NepDate): Pair<Int, Int> {
    val numDays = Constants.bsDaysInMonthByYear[date.year]?.get(date.month - 1)!!

    var firstDay = (date withDayOfMonth 1).dayOfWeek

    //normalize to start day sunday
    if (firstDay >= 7) {
        firstDay = 1
    } else {
        firstDay += 1
    }

    return Pair(firstDay, numDays)
}

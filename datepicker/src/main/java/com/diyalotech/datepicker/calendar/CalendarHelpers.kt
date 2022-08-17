package com.diyalotech.datepicker.calendar

import com.diyalotech.datepicker.ktx.dayOfWeek
import com.diyalotech.datepicker.ktx.withDayOfMonth
import io.github.aagitoex.nepdate.NepDate


internal val YEAR_RANGE = NepDate.MIN.year..NepDate.MAX.year

internal fun getDates(date: NepDate): Pair<Int, Int> {
    val numDays = NepDate.getDaysInMonth(date.year, date.month - 1)

    var firstDay = (date withDayOfMonth 1).dayOfWeek

    //normalize to start day sunday
    if (firstDay >= 7) {
        firstDay = 1
    } else {
        firstDay += 1
    }

    return Pair(firstDay, numDays)
}
